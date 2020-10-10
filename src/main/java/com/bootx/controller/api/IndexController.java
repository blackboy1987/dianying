package com.bootx.controller.api;

import com.bootx.common.Result;
import com.bootx.entity.*;
import com.bootx.service.*;
import com.bootx.service.api.IndexService;
import com.bootx.util.DateUtils;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("apiIndexController")
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IndexService indexService;
    @Autowired
    private AppService appService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private PlayRecordService playRecordService;
    @Autowired
    private SiteInfoService siteInfoService;
    @Autowired
    private VisitRecordService visitRecordService;

    @GetMapping("/categories")
    @JsonView(BaseEntity.ListView.class)
    public Result categories(Long id){
        return Result.success(jdbcTemplate.queryForList("select id,name from moviecategory order by orders asc"));
    }

    @GetMapping("/list")
    @JsonView(BaseEntity.ListView.class)
    public Result list(Long categoryId,Integer pageNumber){
        return Result.success(indexService.list(categoryId, pageNumber));
    }


    @GetMapping("/info")
    @JsonView(BaseEntity.ViewView.class)
    public Result info(Long id){
        return Result.success(movieService.find(id));
    }


    @GetMapping("/search")
    @JsonView(BaseEntity.ListView.class)
    public Result search(String keywords){
        if(StringUtils.isEmpty(keywords)|| StringUtils.isEmpty(keywords.trim())){
          return Result.success(Collections.EMPTY_LIST);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append("movie.id,");
        sb.append("movie.area,");
        sb.append("movie.img,");
        sb.append("movie.lang,");
        sb.append("movie.title,");
        sb.append("movie.remarks,");
        sb.append("movieCategory.name typeName,");
        sb.append("movie.score");
        sb.append(" from");
        sb.append(" movie as movie,");
        sb.append("moviecategory as movieCategory");
        sb.append(" where 1=1");
        sb.append(" and ");
        sb.append(" movie.isShow = true");
        sb.append(" and ");
        sb.append(" movie.movieCategory_id=movieCategory.id ");
        sb.append(" and ");
        sb.append(" movie.title like '%").append(keywords).append("%' ");
        sb.append(" order by movie.score desc limit 20");
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sb.toString());
        // 异步在其他平台更新一下资源看看
        indexService.updateResource(keywords);

        return Result.success(result);
    }

    @GetMapping("/search1")
    public Result search(){
        Map<String, Object> params = new HashMap<>();
        List<Movie> movies = movieService.findAll();
        for (Movie movie:movies) {
            params.put("key",movie.getTitle());
            String result = WebUtils.get("https://bg.zqbkk.cn/app/index.php?i=2&t=0&v=1.0&from=wxapp&c=entry&a=wxapp&do=Search&m=sg_movie&sign=24fea3a05b103e687e2e1daead71f1fa",params);
            JsonRootBean jsonRootBean = JsonUtils.toObject(result, JsonRootBean.class);
            List<Data> data = jsonRootBean.getData();
            if(data.size()==1){
                Data data1 = data.get(0);
                movie.setActors(data1.getVod_actor());
                movie.setDirector(data1.getVod_director());
                movie.setLang(data1.getVod_lang());
                movie.setArea(data1.getVod_area());
                movie.setScore(data1.getVod_score());
                movie.setContent(data1.getVod_content());
                movieService.update(movie);

            }else{
                System.out.println(movie.getTitle()+":"+data.size());
            }
        }
        return Result.success("ok");
    }


    @GetMapping("/site")
    @JsonView(BaseEntity.ViewView.class)
    public Result site(String appCode,String appSecret){
        if(appService.exist(appCode,appSecret)){
            return Result.success(indexService.site(appCode,appSecret));
        }
        return Result.error("不存在");
    }

    @GetMapping("/login")
    public Result index(String code, String appCode, String appSecret){

        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        App app = appService.findByAppCode(appCode);
        Map<String,Object> params = new HashMap<>();
        params.put("appid",app.getAppId());
        params.put("secret",app.getAppSecret());
        params.put("js_code",code);
        params.put("grant_type","authorization_code");
        Map<String,String> result = JsonUtils.toObject(WebUtils.get(url, params), new TypeReference<Map<String, String>>() {});
        Member member = memberService.create(result,app);
        if(member!=null){
            Map<String,Object> data1 = new HashMap<>(result);
            data1.put("id",member.getId());
            data.put("id",member.getId());
            data.putAll(memberService.getData(member));
            data.put("token", JWTUtils.create(member.getId()+"",data1));
        }
        return Result.success(data);
    }

    @GetMapping("/update_time")
    public Result updateTime(Long count,String appCode, String appSecret, String userToken, Date start, Date end,String playUrlKey,Long videoId){
        App app = appService.findByAppCode(appCode);
        if(app == null){
            return Result.error("");
        }
        SiteInfo siteInfo = siteInfoService.find(app);
        if(siteInfo==null || !siteInfo.getOpenPoint() || siteInfo.getEveryMinuteToPoint()==null || siteInfo.getEveryMinuteToPoint()<=0 ||siteInfo.getMinVisitMinute()==null || siteInfo.getMinVisitMinute()<=0){
            return Result.error("");
        }
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        if(member==null){
            return Result.error("");
        }
        Long min = DateUtils.getIntervalMin(end,start);
        if(count<10){
            return Result.error("");
        }
        PlayRecord playRecord = new PlayRecord();
        playRecord.setEnd(end);
        playRecord.setStart(start);
        playRecord.setMember(member);
        playRecord.setPlayUrlKey(playUrlKey);
        playRecord.setVideoId(videoId);
        playRecord.setSeconds(count);
        playRecord = playRecordService.save(playRecord);
        // 奖励积分
        if(min<siteInfo.getMinVisitMinute()){
            return Result.error("");
        }else{
            memberService.addPoint(member,playRecord.getSeconds(), PointLog.Type.reward,"看视频奖励");
        }


        return Result.success("bbb");
    }

    @GetMapping("/adjust")
    public Result adjust(String appCode, String appSecret, String userToken, Long point, String memo){
        App app = appService.findByAppCode(appCode);
        if(app == null){
            return Result.error("");
        }
        SiteInfo siteInfo = siteInfoService.find(app);
        if(siteInfo==null || siteInfo.getJumpAdDiscoutPoint()==null || siteInfo.getJumpAdDiscoutPoint()<=0){
            return Result.error("");
        }
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        if(member==null){
            return Result.error("");
        }
        if(member.getPoint()+point>0){
            // 积分调整
            memberService.addPoint(member,point, PointLog.Type.adjustment,memo);
            return Result.success("bbb");
        }else{
            return Result.error("积分不足，无法为您跳过广告");
        }
    }

    @GetMapping("/user")
    public Result user(String appCode, String appSecret, String userToken){
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        return Result.success(memberService.getData(member));

    }

    @GetMapping("/update")
    public Result index(String appCode, String appSecret,String userToken,Member memberInfo){
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        if(member!=null){
            member.setNickName(memberInfo.getNickName());
            member.setGender(memberInfo.getGender());
            member.setCity(memberInfo.getCity());
            member.setProvince(memberInfo.getProvince());
            member.setCountry(memberInfo.getCountry());
            member.setAvatarUrl(memberInfo.getAvatarUrl());
            member.setIsAuth(true);
            memberService.update(member);
        }
        return Result.success(memberService.getData(member));
    }

    @GetMapping("/record")
    public Result record(String appCode, String appSecret,String userToken,Long videoId){
        if(StringUtils.isEmpty(userToken)){
            return Result.error("未登录");
        }
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        Movie movie = movieService.find(videoId);
        if(member!=null&&movie!=null){
            VisitRecord visitRecord = new VisitRecord();
            visitRecord.setCategoryName(movie.getMovieCategory().getName());
            visitRecord.setImg(movie.getImg());
            visitRecord.setLang(movie.getLang());
            visitRecord.setMovieId(videoId);
            visitRecord.setTitle(movie.getTitle());
            visitRecord.setMemberId(member.getId());
            visitRecordService.save(visitRecord);
        }
        return Result.success("");
    }

    @GetMapping("/record_list")
    public Result recordList(String appCode, String appSecret,String userToken){
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        if(member!=null){
            return Result.success(jdbcTemplate.queryForList("select img,lang,movieId,title from visitrecord where memberId=? order by createdDate desc limit 20",member.getId()));
        }
        return Result.success("");
    }



    @GetMapping("/danMu_list")
    public Result danMuList(String appCode, String appSecret,Long id,String key){
        return Result.success(jdbcTemplate.queryForList("select tex,color,`time` from danmu where videoId=? and playUrlKey=?",id,key));
    }

    @GetMapping("/tuijian")
    public Result tuijian(String appCode, String appSecret,Long parentId,String token){
        Member parent = memberService.find(parentId);
        Member current = memberService.findByUserTokenAndApp(token,appService.findByAppCode(appCode));





        if(parent==null||current==null||parent==current){
            return Result.success("");
        }
        if(current.getGrade()>=parent.getGrade()){
            return Result.success("");
        }
        if(parent.getChildren().contains(current)){
            return Result.success("");
        }






        current.setParent(parent);
        current.setGrade(parent.getGrade()+1);
        memberService.update(current);
        return Result.success("");
    }
}
