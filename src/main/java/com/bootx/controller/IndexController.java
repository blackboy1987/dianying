package com.bootx.controller;

import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.PlayUrlService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.localhost.LocalMovie;
import com.bootx.vo.localhost.LocalPlayUrl;
import com.bootx.vo.localhost.LocalResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private PlayUrlService playUrlService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public LocalResponse index(String keywords) throws UnsupportedEncodingException {
        String url = "http://localhost/api.php?wd="+keywords;
        String result = WebUtils.get(url, null);
        String dataStr = result.substring(1,result.length()-2);


        LocalResponse localResponse = JsonUtils.toObject(dataStr,LocalResponse.class);
        for (LocalMovie localMovie:localResponse.getInfo()) {
            localMovie.setTitle(URLDecoder.decode(localMovie.getTitle(),"utf-8"));
            parseLocalMovie(localMovie);
        }



        return localResponse;
    }

    @GetMapping("/ok")
    public String ok(){
        Integer count = 0;
        for (long i=70000;i<=80000;i++){
            boolean aBoolean = stringRedisTemplate.hasKey("local_" + i);
            if(aBoolean){
                continue;
            }
            count++;
            LocalMovie localMovie = new LocalMovie();
            localMovie.setId(i);
            new Thread(()->{
                try {
                    parseLocalMovie(localMovie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return ""+count;
    }

    private LocalMovie parseLocalMovie(LocalMovie localMovie) throws UnsupportedEncodingException {
        String detailUrl = "http://localhost/api.php?flag=0&id=";
        // 解析每个movie
        Map<String,Object> params = new HashMap<>();
        String movieStr = WebUtils.post(detailUrl+localMovie.getId(),params);
        System.out.println(detailUrl+localMovie.getId());
        System.out.println(movieStr);
        LocalMovie movie1 = JsonUtils.toObject(movieStr.substring(1,movieStr.length()-2),LocalMovie.class);
        if(StringUtils.isNotEmpty(movie1.getTitle())){
            localMovie.setPic(movie1.getPic());
            localMovie.setType(movie1.getType());
            localMovie.setInfo(movie1.getInfo());
            try{
                localMovie.setTitle(URLDecoder.decode(movie1.getTitle(),"utf-8"));
            }catch (Exception e){
                e.printStackTrace();
            }
            Movie movie = transform(localMovie);
            try{
                movieService.save(movie);
            }catch (Exception e){
                movie = movieService.findByVideoId(movie1.getVideoId());
                if(movie!=null){
                    movie = transform(localMovie);
                    movieService.update(movie);
                }
            }
        }

        return localMovie;
    }

    private Movie transform(LocalMovie localMovie) {
        Movie movie = new Movie();
        movie.setImg(localMovie.getPic());
        movie.setTitle(localMovie.getTitle());
        movie.setVideoId(localMovie.getVideoId());
        Set<PlayUrl> playUrls = new HashSet<>();
        for (LocalPlayUrl localPlayUrl:localMovie.getInfo()) {
            PlayUrl playUrl = new PlayUrl();
            playUrl.setTitle(localPlayUrl.getName());
            playUrl.setUrls(localPlayUrl.getVideo());
            playUrl.setMovie(movie);
            playUrls.add(playUrl);
        }
        playUrls.stream().sorted();
        movie.setPlayUrls(playUrls);

        return movie;


    }


    @GetMapping("/category")
    public String category(){
        MovieCategory movieCategory = new MovieCategory();
        movieCategory.setName("电影");
        movieCategoryService.save(movieCategory);


        MovieCategory movieCategory1 = new MovieCategory();
        movieCategory1.setName("电视剧");
        movieCategoryService.save(movieCategory1);


        MovieCategory movieCategory2 = new MovieCategory();
        movieCategory2.setName("动漫");
        movieCategoryService.save(movieCategory2);


        MovieCategory movieCategory3 = new MovieCategory();
        movieCategory3.setName("综艺");
        movieCategoryService.save(movieCategory3);

        return "ok";
    }


    @GetMapping("/redis")
    public String redis(){
        Integer count = 0;
        List<Movie> movies = movieService.findAll();
        for (Movie movie:movies){
            stringRedisTemplate.opsForValue().set(movie.getVideoId(),movie.getId()+"");
            System.out.println(movie.getId()+"成功");
            count ++;
        }
        return count+"";
    }

    @GetMapping("/update")
    public String update(){
        Integer count = 0;

        MovieCategory dianying = movieCategoryService.find(1L);
        MovieCategory dianshiju = movieCategoryService.find(2L);

        List<PlayUrl> playUrls = playUrlService.findAll();
        for (PlayUrl playUrl:playUrls){
            Movie movie = playUrl.getMovie();
            String url = playUrl.getUrls().get(0);
            if(url.indexOf("第01集")==0){
                jdbcTemplate.update("insert movie_moviecategories (movies_id, movieCategories_id) value (?,?)",movie.getId(),dianshiju.getId());
            }
            if(url.indexOf("HD高清")==0){
                jdbcTemplate.update("insert movie_moviecategories (movies_id, movieCategories_id) value (?,?)",movie.getId(),dianying.getId());
            }
        }
        return count+"";
    }

}
