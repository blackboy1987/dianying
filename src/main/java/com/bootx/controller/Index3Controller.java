package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.MovieTagService;
import com.bootx.service.PlayUrlService;
import com.bootx.util.DateUtils;
import com.bootx.vo.okzy.com.jsoncn.pojo.Category;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/index3")
public class Index3Controller {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieTagService movieTagService;
    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private PlayUrlService playUrlService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/category")
    public String category(){
        JsonRootBean jsonRootBean = Demo1.category();
        for (Category category:jsonRootBean.getCategory()) {
            MovieCategory movieCategory = movieCategoryService.findByName(category.getType_name());
            if(movieCategory==null){
                movieCategory = new MovieCategory();
            }
            movieCategory.setName(category.getType_name());
            movieCategory.setOtherId("okzy_"+category.getType_id());
            if(movieCategory.isNew()){
                movieCategoryService.save(movieCategory);
            }else{
                movieCategoryService.update(movieCategory);
            }
        }
        return "ok";
    }


    @GetMapping("/detail")
    public String detail() throws Exception{
        // 14:03:30
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        Integer pagecount = Demo1.category().getPagecount();
        System.out.println(pagecount);
        for (int page=1;page<=pagecount;page++) {
            List<Data> list = Demo1.detail(page);
            for (Data data:list) {
                boolean aBoolean = stringRedisTemplate.hasKey("okzy_" + data.getVod_id());
                if(aBoolean){
                    continue;
                }
                threadPool.submit(()->{
                    Movie movie = save(data);
                });
            }
            System.out.println(new Date()+"page========================================"+page);
        }

        threadPool.shutdown();
        return "jsonRootBean";
    }


    public Movie save(Data data) {
        Movie movie =  movieService.findByVideoId("okzy_"+data.getVod_id());
        if(movie==null){
            movie = new Movie();
            movie.setVideoId("okzy_"+data.getVod_id());
            movie.setTitle(data.getVod_name());
            movie.setImg(data.getVod_pic());
            movie =  movieService.save(movie);
            setInfo(movie,data);
            // 处理播放地址的问题
            parseUrl(movie,data.getVod_play_url());
        }else{
            movie.setScore(data.getVod_score());
            try {
                movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(), "yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                try {
                    movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(), "yyyy-MM-dd"));
                } catch (Exception e1) {

                }
            }
        }
        return  movieService.update(movie);
    }


    private void setInfo(Movie movie, Data data) {
        movie.setContent(data.getVod_content());
        movie.setScore(data.getVod_score());
        movie.setArea(data.getVod_area());
        movie.setLang(data.getVod_lang());
        movie.setActors(data.getVod_actor());
        movie.setDirector(data.getVod_director());
        movie.setYear(data.getVod_year());

        movie.setRemarks(data.getVod_remarks());
        movie.setStatus(data.getVod_status());
        movie.setSub(data.getVod_sub());
        movie.setEnglish(data.getVod_en());
        movie.setLetter(data.getVod_letter());
        movie.setBehind(data.getVod_behind());
        movie.setBlurb(data.getVod_blurb());
        movie.setSerial(data.getVod_serial());


        movie.setMovieCategory(movieCategoryService.findByOtherId("okzy_"+data.getType_id()));
        if(movie.getMovieCategory()==null){
            System.out.println("分类是空的。"+data.getType_name()+":"+data.getType_id()+":"+data.getVod_class());
        }

        try {
            movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            try {
                movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(), "yyyy-MM-dd"));
            } catch (Exception e1) {

            }
        }
    }

    private void parseUrl(Movie movie,String vodPlayUrl) {
        String[] playUrlsArray = vodPlayUrl.split(":::");
        Set<PlayUrl> playUrls = new HashSet<>();
        int index = 0;
        for (String u:playUrlsArray) {
            PlayUrl playUrl = new PlayUrl();
            playUrl.setTitle("线路"+(++index));
            playUrl.setMovie(movie);
            String[] jishusArray = u.split("#");
            playUrl.getUrls().addAll(Arrays.asList(jishusArray));
            playUrls.add(playUrl);
        }
        movie.setPlayUrls(playUrls);

    }

}
