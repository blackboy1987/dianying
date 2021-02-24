package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.Movie1;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.SiteInfo;
import com.bootx.service.Movie1Service;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.SiteInfoService;
import com.bootx.service.api.IndexService;
import com.bootx.util.DateUtils;
import com.bootx.vo.list.JsonRootBean;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class IndexController {


    @Autowired
    private IndexService indexService;

    @Autowired
    private SiteInfoService siteInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private Movie1Service movie1Service;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/aa")
    public String aa(Integer page){
        // 1029
        if(page==null){
            page = 1;
        }
        JsonRootBean list = Demo1.list(page);

        parse(list.getList());

        int pagecount = list.getPagecount();
        while (pagecount>page){
            page = page+1;
            list = Demo1.list(page);
            parse(list.getList());
        }

        return "ok";
    }

    private void parse(List<com.bootx.vo.list.List> list) {
        List<MovieCategory> all = movieCategoryService.findAll();
        Map<String,MovieCategory> map = new HashMap<>();
        all.stream().forEach(item->map.put(item.getOtherId(),item));
        for (com.bootx.vo.list.List mmovie:list) {
            Movie1 movie1 = new Movie1();
            movie1.setTitle(mmovie.getVod_name());
            movie1.setMovieCategory(map.get("okzy_"+mmovie.getType_id()));
            movie1.setEnglish(mmovie.getVod_en());
            movie1.setTime(DateUtils.formatStringToDate(mmovie.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setUpdateTime(DateUtils.formatStringToDate(mmovie.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setRemarks(mmovie.getVod_remarks());
            movie1.setPlayFrom(mmovie.getVod_play_from());
            movie1.setVideoId("okzy_"+mmovie.getVod_id());
            try {
                movie1Service.save(movie1);
                stringRedisTemplate.opsForValue().set(mmovie.getVod_id()+"",mmovie.getVod_name());
            }catch (Exception e){
                // e.printStackTrace();
            }
        }
    }


    @GetMapping("/site_update")
    public int siteUpdate(int status){
        SiteInfo siteInfo = siteInfoService.find(1L);
        if(siteInfo!=null){
            siteInfo.setStatus(status);
            siteInfoService.update(siteInfo);
        }
        return 1/0;
    }

    @GetMapping("/detail")
    public String detail(Integer start,Integer end) throws InterruptedException {
        if(start==null||start<=0){
            start = 1;
        }
        if(end==null||end<=0 || end<start){
            end = start+100000;
        }
        for (Integer i=start;i<end;i++) {
            Integer integer = jdbcTemplate.queryForObject("select count(id) from movie2.playurl where movie1_id= (select id from movie2.movie1 where videoId='okzy_" + i + "')", Integer.class);
            if(integer>0){
                System.out.println(i+"==================="+integer);
                continue;
            }
            detail(i +"");
        }
        return "ok";
    }

    @GetMapping("/detail3")
    public String detail3(){
        for (Integer i = 0; i < 100000; i++) {
            Boolean aBoolean = stringRedisTemplate.hasKey("okzy_"+i);
            if(!aBoolean){
               try {
                   Integer finalI = i;
                   new Thread(()->{
                       detail(finalI +"");
                       stringRedisTemplate.opsForValue().set("okzy_"+ finalI,"okzy_"+ finalI);
                   }).start();;
                   Thread.sleep(380);
               }catch (Exception e){
                   e.printStackTrace();
               }
            }else{
                System.out.println("缓存存在，忽略================"+i);
            }
        }
        return "ok";
    }

    @GetMapping("/add_cache")
    public String addCache(){
        List<Movie1> movie1s = movie1Service.findAll();
        for (Movie1 movie1:movie1s) {
            stringRedisTemplate.opsForValue().set(movie1.getVideoId(),movie1.getVideoId());
        }
        return "ok";
    }



    /**
     * 根据vod_id 获取详细信息
     * @param vodId
     * @return
     */
    @GetMapping("/detail1")
    public String detail(String vodId){
        System.out.println(new Date()+"========================"+vodId);
        List<Data> detail = Demo1.detail(vodId);
        for (Data data:detail) {
            Movie1 movie1 = movie1Service.findByVideoId("okzy_" + data.getVod_id());
            if(movie1!=null){
                movie1 = movie1Service.copy(movie1,data);
                movie1.setMovieCategory(movieCategoryService.findByOtherId("okzy_"+data.getType_id()));
                movie1Service.update(movie1);
            }else{
                movie1=new Movie1();
                movie1.setEnglish(data.getVod_en());
                movie1.setPlayFrom(data.getVod_play_from());
                movie1.setRemarks(data.getVod_remarks());
                movie1.setTime(DateUtils.formatStringToDate(data.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
                movie1.setUpdateTime(DateUtils.formatStringToDate(data.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
                movie1.setAddTime(new Date(data.getVod_time_add()));
                movie1.setTitle(data.getVod_name());
                movie1.setVideoId("okzy_"+data.getVod_id());
                movie1.setIsShow(true);
                movie1.setMovieCategory(movieCategoryService.findByOtherId("okzy_"+data.getType_id()));
                movie1 = movie1Service.copy(movie1,data);
                movie1Service.save(movie1);
                System.out.println("不存在："+data.getVod_id());
            }
        }
        return "abc";
    }
}
