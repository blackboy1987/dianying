
package com.bootx.service.impl;

import com.bootx.Demo;
import com.bootx.dao.MovieDao;
import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.MovieTag;
import com.bootx.entity.PlayUrl;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.MovieTagService;
import com.bootx.util.DateUtils;
import com.bootx.vo.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long> implements MovieService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MovieTagService movieTagService;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable(value = "movie",key = "#id")
    public Movie find(Long id) {
        Movie movie = super.find(id);
        Set<PlayUrl> playUrls = movie.getPlayUrls().stream().filter(PlayUrl::getIsEnabled).collect(Collectors.toSet());
        movie.setPlayUrls(playUrls);
        return movie;
    }

    @Override
    public Movie findByVideoId(String videoId) {
        return movieDao.find("videoId",videoId);
    }

    @Override
    public Movie findByTitle(String title) {
        return movieDao.find("title",title);
    }

    @Override
    public void sync() {
        Integer page = 1;
        Boolean flag = true;
        while (flag){
            List<Data> dataList = Demo.main5(null,page);
            if(dataList.isEmpty()){
                flag = false;
            }else{
                System.out.println(new Date()+":"+page);
                page = page+1;
                for (Data data:dataList) {
                    Movie movie = save(data);
                    find(movie.getId());
                    stringRedisTemplate.opsForValue().set(movie.getVideoId(),movie.getId()+"");
                }
            }

        }
    }

    private Movie save(Data data) {
        Movie movie = findByVideoId("igomall_"+data.getVod_id());
        if(movie==null){
            movie = new Movie();
            movie.setIsShow(true);
            movie.setVideoId("igomall_"+data.getVod_id());
            movie.setTitle(data.getVod_name());
            movie.setImg(data.getVod_pic());
            movie = super.save(movie);
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
        return super.update(updateOther(movie,data));
    }

    private Movie updateOther(Movie movie, Data data) {
        movie.setYear(data.getVod_year());
        return movie;
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