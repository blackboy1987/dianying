package com.bootx.controller.api;

import com.bootx.common.Result;
import com.bootx.entity.*;
import com.bootx.service.*;
import com.bootx.service.api.IndexService;
import com.bootx.util.DateUtils;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
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
    private MovieCategoryService movieCategoryService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IndexService indexService;
    @Autowired
    private AppService appService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private Movie1Service movie1Service;
    @Autowired
    private SiteInfoService siteInfoService;
    @Autowired
    private VisitRecordService visitRecordService;
    @Autowired
    private PlayRecordService playRecordService;

    /**
     * 分类
     * @return
     */
    @GetMapping("/categories")
    @JsonView(BaseEntity.ListView.class)
    public Result categories(){
        return Result.success(movieCategoryService.list());
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

    @GetMapping("/site")
    @JsonView(BaseEntity.ViewView.class)
    public Result site(String appCode,String appSecret){
        if(appService.exist(appCode,appSecret)){
            return Result.success(indexService.site(appCode,appSecret));
        }
        return Result.error("不存在");
    }

    /**
     * 根据分类拉去数据
     * @param categoryId
     * @param pageNumber
     * @return
     */
    @GetMapping("/list")
    @JsonView(BaseEntity.ListView.class)
    public Result list(Long categoryId,Integer pageNumber){
        return Result.success(indexService.list(categoryId, pageNumber));
    }

    @GetMapping("/info")
    @JsonView(BaseEntity.ViewView.class)
    public Result info(Long id){
        return Result.success(movie1Service.find(id));
    }

    @GetMapping("/record")
    public Result record(String appCode, String appSecret,String userToken,Long videoId){
        if(StringUtils.isEmpty(userToken)){
            return Result.error("未登录");
        }
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        Movie1 movie = movie1Service.find1(videoId);
        if(member!=null&&movie!=null){
            VisitRecord visitRecord = new VisitRecord();
            visitRecord.setCategoryName(movie.getMovieCategory().getName());
            visitRecord.setImg(movie.getPic());
            visitRecord.setLang(movie.getLang());
            visitRecord.setMovieId(videoId);
            visitRecord.setTitle(movie.getTitle());
            visitRecord.setMemberId(member.getId());
            visitRecordService.save(visitRecord);
        }
        return Result.success("");
    }

    @GetMapping("/update_time")
    public Result updateTime(Long count, String appCode, String appSecret, String userToken, Date start, Date end, String playUrlKey, Long videoId){
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

    @GetMapping("/play")
    public Result play(Long id, String currentPlayUrlKey,String appCode,  String appSecret, String userToken){
        Movie1 movie = movie1Service.find(id);
        if(movie!=null){
            Set<PlayUrl> playUrls = movie.getPlayUrls();
            Integer index = Integer.valueOf(currentPlayUrlKey.split("_")[0]);
            Integer i=0;
            Iterator<PlayUrl> iterator = playUrls.iterator();
            while (iterator.hasNext()){
                PlayUrl playUrl = iterator.next();
                if(i==index){
                    // jdbcTemplate.update("update playurl set isEnabled=false,lastModifiedDate=now() where id=?",playUrl.getId());
                }
                i++;
            }
        }

        return Result.success("");

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
        sb.append("movie.pic,");
        sb.append("movie.title,");
        sb.append("movie.remarks,");
        sb.append("movie.year,");
        sb.append("movie.actor,");
        sb.append("movie.area,");
        sb.append("movieCategory.name typeName");
        sb.append(" from");
        sb.append(" movie1 as movie,");
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
        return Result.success(result);
    }

    @GetMapping("/user")
    public Result user(String appCode, String appSecret, String userToken){
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        return Result.success(memberService.getData(member));

    }

    @GetMapping("/record_list")
    public Result recordList(String appCode, String appSecret,String userToken){
        Member member = memberService.findByUserTokenAndApp(userToken,appService.findByAppCode(appCode));
        if(member!=null){
            return Result.success(jdbcTemplate.queryForList("select img,lang,movieId,title from visitrecord where memberId=? order by createdDate desc limit 20",member.getId()));
        }
        return Result.success("");
    }
}
