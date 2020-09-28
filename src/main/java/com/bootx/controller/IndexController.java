package com.bootx.controller;

import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.MovieTag;
import com.bootx.entity.PlayUrl;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.MovieTagService;
import com.bootx.service.PlayUrlService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
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
    private MovieTagService movieTagService;
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
    public String ok() throws Exception{
        Integer count = 0;
        for (long i=1;i<=80000;i++){
            Thread.sleep(20);
            boolean aBoolean = stringRedisTemplate.hasKey("local_" + i);
            if(aBoolean){
                continue;
            }
            count++;
            LocalMovie localMovie = new LocalMovie();
            localMovie.setId(i);
            parseLocalMovie(localMovie);
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
        MovieTag movieTag = new MovieTag();
        movieTag.setName("电影");
        movieTagService.save(movieTag);


        MovieTag movieTag1 = new MovieTag();
        movieTag1.setName("电视剧");
        movieTagService.save(movieTag1);


        MovieTag movieTag2 = new MovieTag();
        movieTag2.setName("动漫");
        movieTagService.save(movieTag2);


        MovieTag movieTag3 = new MovieTag();
        movieTag3.setName("综艺");
        movieTagService.save(movieTag3);

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

        MovieTag dianying = movieTagService.find(1L);
        MovieTag dianshiju = movieTagService.find(2L);

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

    @GetMapping("/update1")
    public Integer update1(){
        Integer count = 0;
        for (Long i = 14393L; i < 80000L; i++) {
            Movie movie1 = movieService.find(i);
            if(movie1==null){
                continue;
            }
            update1(movie1);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------"+movie1.getId());
        }


        return count;
    }

    private void update1(Movie movie1) {
        Map<String,Object> params = new HashMap<>();
        params.put("key",movie1.getTitle());
        String result = WebUtils.get("https://www.i-gomall.com/app/index.php?i=2&t=0&v=1.0&from=wxapp&c=entry&a=wxapp&do=Search&m=sg_movie&sign=eab261ec8246a2efcd6612ec5955a31f",params);
        JsonRootBean jsonRootBean = JsonUtils.toObject(result,JsonRootBean.class);
        if(jsonRootBean.getData().size()>0) {
            new Thread(()->{
                for (Data data : jsonRootBean.getData()) {
                    if (StringUtils.equals(movie1.getTitle(), data.getVod_name())) {
                        setInfo(movie1,data);
                        movieService.update(movie1);
                    } else {
                        Movie movie = movieService.findByTitle(data.getVod_name());
                        if (movie != null) {
                            setInfo(movie,data);
                            movieService.update(movie);
                        } else {
                            save(data);
                        }
                    }
                }
            }).start();
        }
    }

    private void setInfo(Movie movie,Data data){
        movie.setContent(data.getVod_content());
        movie.setScore(data.getVod_score());
        movie.setArea(data.getVod_area());
        movie.setLang(data.getVod_lang());
        movie.setActors(data.getVod_actor());
        movie.setDirector(data.getVod_director());
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


    private void save(Data data) {
        Movie movie = new Movie();
        movie.setVideoId("igomall_"+data.getVod_id());
        movie.setTitle(data.getVod_name());
        movie.setImg(data.getVod_pic());
        setInfo(movie,data);
        if(movie.getId()==null){
            movieService.save(movie);
        }else{
            movieService.update(movie);
        }

    }

    @GetMapping("/tags")
    private String tags(){
        String str = "分类::动作片:喜剧片:爱情片:科幻片:恐怖片:剧情片:战争片:纪录片:动画片:惊悚片:预告片:犯罪片:伦理片:;类型::喜剧:爱情:恐怖:动作:科幻:剧情:战争:警匪:犯罪:动画:奇幻:武侠:冒险:枪战:恐怖:悬疑:惊悚:经典:青春:文艺:微电影:古装:历史:运动:农村:儿童:网络电影:;地区::大陆:香港:台湾:美国:法国:英国:日本:韩国:德国:泰国:印度:意大利:西班牙:加拿大:其他:;年份::2020:2019:2018:2017:2016:2015:2014:2013:2012:2011:2010:;语言::国语:英语:粤语:闽南语:韩语:日语:法语:德语:其它:;字母::A:B:C:D:E:F:G:H:I:J:K:L:M:N:O:P:Q:R:S:T:U:V:W:X:Y:Z:0-9:;";
        String [] str1s = str.split(";");
        System.out.println(str1s.length);
        for (String str1:str1s) {
            // 分类:动作片|喜剧片|爱情片|科幻片|恐怖片|剧情片|战争片|纪录片|动画片|惊悚片|预告片|犯罪片|伦理片|
            MovieTag root = new MovieTag();
            root.setName(str1.split("::")[0]);
            root = movieTagService.save(root);
            String[] childs = str1.split("::")[1].split(":");
            for (String child:childs){
                MovieTag movieTag = new MovieTag();
                movieTag.setName(child);
                movieTag.setParent(root);
                movieTagService.save(movieTag);
            }

        }

        return "ok";
    }


}
