package com.bootx.service.api.impl;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.entity.PlayUrl;
import com.bootx.service.ESService;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.service.api.IndexService;
import com.bootx.util.DateUtils;
import com.bootx.vo.es.MovieES;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private ESService esService;

    @Override
    // @Cacheable(value="siteInfo",key = "#appCode +'-'+ #appSecret",sync=true)
    public Map<String,Object> site(String appCode, String appSecret){
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
        sb.append("siteInfo.status, ");
        sb.append("siteInfo.name ");
        sb.append("from ");
        sb.append("siteInfo as siteInfo, ");
        sb.append("app as app ");
        sb.append("where app.appCode='"+appCode+"' ");
        sb.append("and app.token='"+appSecret+"' ");
        sb.append("and siteInfo.appId=app.id");
        try {
            return jdbcTemplate.queryForMap(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable(value = "movie",key = "'list_'+#categoryId +'_'+ #pageNumber")
    public Object list(Long categoryId,Integer pageNumber){
        Map<String,Object> data = new HashMap<>();
        if(categoryId==null){
            StringBuffer sb = new StringBuffer();
            sb.append("movie.id,");
            sb.append("movie.area,");
            sb.append("movie.img,");
            sb.append("movie.lang,");
            sb.append("movie.title,");
            sb.append("movie.remarks,");
            sb.append("movieCategory.name typeName,");
            sb.append("movie.score");
            data.put("hotMovies", jdbcTemplate.queryForList("select "+sb.toString()+" from movie as movie,moviecategory as movieCategory where movie.movieCategory_id=movieCategory.id and ( movieCategory.id = 1 OR movieCategory.treePath like '%,1,%') order by movie.score desc LIMIT 30"));
            data.put("hottv", jdbcTemplate.queryForList("select "+sb.toString()+" from movie as movie,moviecategory as movieCategory where movie.movieCategory_id=movieCategory.id and ( movieCategory.id = 2 OR movieCategory.treePath like '%,2,%') order by movie.score desc LIMIT 30"));
            data.put("news", jdbcTemplate.queryForList("select "+sb.toString()+" from movie as movie,moviecategory as movieCategory where movie.movieCategory_id=movieCategory.id order by movie.updateTime desc limit 30"));
            return data;
        }
        return movies(categoryId,pageNumber);
    }

    private List<Map<String,Object>> movies(Long categoryId, Integer pageNumber) {
        if(categoryId==null){
            return Collections.emptyList();
        }
        if(pageNumber==null || pageNumber<0){
            pageNumber = 1;
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

        if(categoryId>10000){
            sb.append(" movie_movietags as movieTag");
            sb.append(" where 1=1");
            sb.append(" and movieTag.movieTags_id=").append(categoryId);
            sb.append(" and movieTag.movies_id=movie.id");
        }else{
            sb.append("moviecategory as movieCategory");
            sb.append(" where 1=1");
            sb.append(" and ");
            sb.append(" movie.movieCategory_id=movieCategory.id ");
            sb.append(" and ");
            sb.append( " ( movieCategory.id = "+categoryId+" OR movieCategory.treePath like '%,"+categoryId+",%') ");
        }
        sb.append(" order by movie.score desc limit "+(pageNumber-1)*30+", 30");
        return jdbcTemplate.queryForList(sb.toString());
    }

    @Override
    @Async
    public void updateResource(String keywords) {
        Integer current = 1;
        JsonRootBean jsonRootBean = Demo1.search(keywords,current);
        Integer totalPage = jsonRootBean.getPagecount();
        save(jsonRootBean.getData());
        if(current<totalPage){
            updateResource(keywords,totalPage,2);
        }
    }

    public void updateResource(String keywords,Integer totalPage,Integer current) {
        for (int i = current;i<=totalPage;i++) {
            JsonRootBean jsonRootBean = Demo1.search(keywords,current);
            if(jsonRootBean.getData().isEmpty()){
                break;
            }
            save(jsonRootBean.getData());
        }
    }

    private void save(List<Data> datas) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (Data data:datas) {
            threadPool.submit(()->{
                Movie movie = save(data);
                stringRedisTemplate.opsForValue().set("okzy_" + data.getVod_id(),movie.getId()+"");
            });
        }
    }

    @Override
    public Movie save(Data data) {
        Movie movie =  movieService.findByVideoId("okzy_"+data.getVod_id());
        if(movie==null){
            movie = new Movie();
            movie.setVideoId("okzy_"+data.getVod_id());
            movie.setTitle(data.getVod_name());
            movie.setImg(data.getVod_pic());
            movie =  movieService.save(movie);
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
        setInfo(movie,data);
        // 处理播放地址的问题
        parseUrl(movie,data.getVod_play_url());
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

    @Override
    public void es() throws IOException {

        List<Movie> movies = movieService.findAll();
        for (Movie movie:movies) {
            System.out.println(movie.getId());
            MovieES movieES = toESModel(movie);
            esService.put(movieES);
        }
    }

    private MovieES toESModel(Movie movie) {
        MovieES movieES = new MovieES();
        movieES.setActors(movie.getActors());
        movieES.setArea(movie.getArea());
        movieES.setBehind(movie.getBehind());
        movieES.setBlurb(movie.getBlurb());
        movieES.setContent(movie.getContent());
        movieES.setDirector(movie.getDirector());
        movieES.setEnglish(movie.getEnglish());
        movieES.setId(movie.getId());
        movieES.setImg(movie.getImg());
        movieES.setLang(movie.getLang());
        movieES.setLetter(movie.getLetter());
        movieES.setRemarks(movie.getRemarks());
        movieES.setScore(movie.getScore());
        movieES.setSerial(movie.getSerial());
        movieES.setStatus(movie.getStatus());
        movieES.setSub(movie.getSub());
        movieES.setTitle(movie.getTitle());

        movieES.setUpdateTime(movie.getUpdateTime());
        movieES.setVideoId(movie.getVideoId());
        movieES.setYear(movie.getYear());
        MovieES.Category category = new MovieES.Category();

        if(movie.getMovieCategory()!=null){
            movieES.setTypeName(movie.getMovieCategory().getName());
            category.setId(movie.getMovieCategory().getId());
            category.setName(movie.getMovieCategory().getName());
            movieES.setCategoryIds(movie.getMovieCategory().getTreePath()+movie.getMovieCategory().getId()+",");
        }

        movieES.setCategory(category);
        return movieES;
    }
}
