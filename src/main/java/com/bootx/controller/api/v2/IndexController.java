package com.bootx.controller.api.v2;

import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Member;
import com.bootx.entity.SiteInfo;
import com.bootx.es.service.EsMovieService;
import com.bootx.service.AppService;
import com.bootx.service.MemberService;
import com.bootx.service.Movie1Service;
import com.bootx.service.SiteInfoService;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiIndexV2Controller")
@RequestMapping("/api/v2")
public class IndexController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AppService appService;

    @Autowired
    private MemberService memberService;
    @Resource
    private SiteInfoService siteInfoService;
    @Resource
    private Movie1Service movie1Service;
    @Resource
    private EsMovieService esMovieService;

    @PostMapping("/config")
    public Result config(String appCode){
        App app = appService.findByAppCode(appCode);
        if(app==null){
            return Result.error("未认证的应用");
        }

        SiteInfo siteInfo = siteInfoService.find(app);

        return Result.success(siteInfo);
    }

    /**
     * 登录
     * @param code
     *      code 码
     * @param appCode
     * @param appSecret
     * @return
     */
    @PostMapping("/login")
    public Result index(String code, String appCode, String appSecret, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = JWTUtils.parseToken(token);
        System.out.println(claims.get("id",Long.class));

        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        App app = appService.findByAppCode(appCode);
        if(app==null){
            return Result.error("未认证的应用");
        }
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

    @PostMapping("/index")
    public Result index(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("tag","最新电影");
        map.put("list",jdbcTemplate.queryForList("select id,title,pic,actor,remarks,score,movieCategory_id from movie1 where movieCategory_id in (1,6,7,8,9,10,11,12) order by `time` desc limit 30"));

        Map<String,Object> map1 = new HashMap<>();
        map1.put("tag","动画动漫");
        map1.put("list",jdbcTemplate.queryForList("select id,title,pic,actor,remarks,score,movieCategory_id from movie1 where movieCategory_id in (4,29,30,31,32) order by `time` desc limit 30"));

        Map<String,Object> map2 = new HashMap<>();
        map2.put("tag","热门综艺");
        map2.put("list",jdbcTemplate.queryForList("select id,title,pic,actor,remarks,score,movieCategory_id from movie1 where movieCategory_id in (3,24,25,26,27) order by `time` desc limit 30"));

        Map<String,Object> map3 = new HashMap<>();
        map3.put("tag","同步剧场");
        map3.put("list",jdbcTemplate.queryForList("select id,title,pic,actor,remarks,score,movieCategory_id from movie1 where movieCategory_id in (2,13,14,15,16) order by `time` desc limit 30"));

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);

        return Result.success(list);
    }

    @PostMapping("/detail")
    @JsonView(BaseEntity.ViewView.class)
    public Result detail(Long id){
        return Result.success(movie1Service.find(id));
    }


}
