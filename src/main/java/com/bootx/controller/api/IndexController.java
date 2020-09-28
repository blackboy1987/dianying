package com.bootx.controller.api;

import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Movie;
import com.bootx.service.MovieService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiIndexController")
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/categories")
    @JsonView(BaseEntity.ListView.class)
    public Result categories(Long id){
        return Result.success(jdbcTemplate.queryForList("select id,name from moviecategory order by orders asc"));
    }

    @GetMapping("/list")
    @JsonView(BaseEntity.ListView.class)
    public Result list(Long categoryId,Integer pageNumber){
        Map<String,Object> data = new HashMap<>();
        if(categoryId==null){
            data.put("hotMovies", jdbcTemplate.queryForList("select * from movie limit 18"));
            data.put("hottv", jdbcTemplate.queryForList("select * from movie limit 18"));
            data.put("news", jdbcTemplate.queryForList("select * from movie limit 18"));
            return Result.success(data);
        }
        return Result.success(movies(categoryId,pageNumber));
    }

    private List<Map<String,Object>> movies(Long categoryId,Integer pageNumber) {
        if(categoryId==null){
            return Collections.emptyList();
        }
        if(pageNumber==null || pageNumber<0){
            pageNumber = 1;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append(" *");
        sb.append(" from");
        sb.append(" movie as movie,");

        if(categoryId>10000){
            sb.append(" movie_movietags as movieTag");
            sb.append(" where 1=1");
            sb.append(" and movieTag.movieTags_id=").append(categoryId);
            sb.append(" and movieTag.movies_id=movie.id");
        }else{
            sb.append(" movie_moviecategories as moveTag");
            sb.append(" where 1=1");
            sb.append(" and moveTag.movieCategories_id=").append(categoryId);
            sb.append(" and moveTag.movies_id=movie.id");
        }
        sb.append(" limit "+(pageNumber-1)*18+", 18");
        return jdbcTemplate.queryForList(sb.toString());


    }


    @GetMapping("/info")
    @JsonView(BaseEntity.ViewView.class)
    public Result info(Long id){
        return Result.success(movieService.find(id));
    }


    @GetMapping("/search")
    @JsonView(BaseEntity.ListView.class)
    public Result search(String keywords){
        return Result.success(jdbcTemplate.queryForList("select * from movie where title like '%"+keywords+"%' limit 20"));
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
    public Result info(String appCode,String appSecret){
        StringBuffer sb =new StringBuffer();

        sb.append("select ");
        sb.append("siteInfo.logo, ");
        sb.append("siteInfo.bannerAdId, ");
        sb.append("siteInfo.gridAdId, ");
        sb.append("siteInfo.interstitialAdId, ");
        sb.append("siteInfo.nativeAdId, ");
        sb.append("siteInfo.rewardedVideoAdId, ");
        sb.append("siteInfo.videoAdId, ");
        sb.append("siteInfo.videoFrontAdId, ");
        sb.append("siteInfo.name ");
        sb.append("from ");
        sb.append("siteInfo as siteInfo, ");
        sb.append("app as app ");
        sb.append("where app.appCode='"+appCode+"' ");
        sb.append("and app.token='"+appSecret+"' ");
        sb.append("and siteInfo.appId=app.id");
        return Result.success(jdbcTemplate.queryForMap(sb.toString()));
    }


}
