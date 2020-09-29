package com.bootx.controller;

import com.bootx.Demo;
import com.bootx.common.Result;
import com.bootx.entity.*;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.MovieTagService;
import com.bootx.service.PlayUrlService;
import com.bootx.util.DateUtils;
import com.bootx.vo.Data;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController("Index2Controller")
@RequestMapping("/index2")
public class Index2Controller {

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

    @GetMapping
    @JsonView(BaseEntity.ViewView.class)
    public Result index(){
        for (int i = 1; i < 9999999; i++) {
            Data data = Demo.main4(i + "");
            if(data!=null){
                Movie movie = save(data);
                return Result.success(movie);
            }
        }
        return Result.success(null);
    }

































    private Movie save(Data data) {
        Movie movie = movieService.findByVideoId("igomall_"+data.getVod_id());
        if(movie==null){
            movie = new Movie();
            movie.setVideoId("igomall_"+data.getVod_id());
        }

        movie.setTitle(data.getVod_name());
        movie.setImg(data.getVod_pic());
        setInfo(movie,data);
        // 处理播放地址的问题
        parseUrl(movie,data.getVod_play_url());
        if(movie.getId()==null){
            movieService.save(movie);
        }else{
           movieService.update(movie);
        }

        return movie;
    }

    private void parseUrl(Movie movie,String vodPlayUrl) {
        String[] playUrlsArray = vodPlayUrl.split(":::");
        int index = 0;
        for (String u:playUrlsArray) {
            PlayUrl playUrl = new PlayUrl();
            playUrl.setTitle("线路"+(++index));
            playUrl.setMovie(movie);
            String[] jishusArray = u.split("#");
            playUrl.getUrls().addAll(Arrays.asList(jishusArray));
            movie.getPlayUrls().add(playUrl);
        }

    }


    private void setInfo(Movie movie,Data data){
        movie.setContent(data.getVod_content());
        movie.setScore(data.getVod_score());
        movie.setArea(data.getVod_area());
        movie.setLang(data.getVod_lang());
        movie.setActors(data.getVod_actor());
        movie.setDirector(data.getVod_director());
        try {
            movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
        }catch (Exception e){
            try {
                movie.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(),"yyyy-MM-dd"));
            }catch (Exception e1){

            }
        }
        if(movie.getId()==null){
            movie = movieService.save(movie);
        }else{
            movie = movieService.update(movie);
        }
// vod_letter
        if(StringUtils.isNotEmpty(data.getVod_letter())){
            MovieTag movieTag = movieTagService.findByName(data.getVod_letter());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getVod_letter());
                movieTag = movieTagService.save(movieTag);
            }




            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }
// vod_class
        if(StringUtils.isNotEmpty(data.getVod_class())){
            MovieTag movieTag = movieTagService.findByName(data.getVod_class());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getVod_class());
                movieTag = movieTagService.save(movieTag);
            }
            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }

// vod_area
        if(StringUtils.isNotEmpty(data.getVod_area())){
            MovieTag movieTag = movieTagService.findByName(data.getVod_area());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getVod_area());
                movieTag = movieTagService.save(movieTag);
            }

            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }

// vod_lang
        if(StringUtils.isNotEmpty(data.getVod_lang())){
            MovieTag movieTag = movieTagService.findByName(data.getVod_lang());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getVod_lang());
                movieTag = movieTagService.save(movieTag);
            }

            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }

// vod_year
        if(StringUtils.isNotEmpty(data.getVod_year())){
            MovieTag movieTag = movieTagService.findByName(data.getVod_year());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getVod_year());
                movieTag = movieTagService.save(movieTag);
            }

            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }

// type_name
        if(StringUtils.isNotEmpty(data.getType_name())){
            MovieTag movieTag = movieTagService.findByName(data.getType_name());
            if(movieTag == null){
                movieTag = new MovieTag();
                movieTag.setName(data.getType_name());
                movieTag = movieTagService.save(movieTag);
            }
            try {
                jdbcTemplate.update("insert movie_movietags (movies_id, movieTags_id) value (?,?)",movie.getId(),movieTag.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }


        // 设置category
        if(StringUtils.isNotEmpty(data.getType_name())){
            MovieCategory movieCategory = movieCategoryService.findByName(data.getType_name());
            if(movieCategory == null){
                movieCategory = new MovieCategory();
                movieCategory.setName(data.getType_name());
                movieCategory = movieCategoryService.save(movieCategory);
            }
            try{
                jdbcTemplate.update("insert movie_moviecategories (movies_id, movieCategories_id) value (?,?)",movie.getId(),movieCategory.getId());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }
    }

}
