package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.*;
import com.bootx.service.Movie1Service;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.SiteInfoService;
import com.bootx.service.api.IndexService;
import com.bootx.util.DateUtils;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
public class IndexController {


    @Autowired
    private IndexService indexService;

    @Autowired
    private SiteInfoService siteInfoService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private Movie1Service movie1Service;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 根据分类来拉取数据
     * @return
     */
    @GetMapping("/category_all_list")
    public int categoryAllList(){
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        for (MovieCategory movieCategory:movieCategories) {
            categoryList(movieCategory.getId());
        }
        return 2;
    }

    @GetMapping("/category_list")
    public int categoryList(Long categoryId){
        MovieCategory movieCategory = movieCategoryService.find(categoryId);
        if(movieCategory!=null){
            int page = 1;
            com.bootx.vo.list.JsonRootBean list = Demo1.list(movieCategory.getOtherId().replace("okzy_",""), page++);
            int pagecount = list.getPagecount();
            List<com.bootx.vo.list.List> list1 = list.getList();
            save(list1,movieCategory);
            while (page<pagecount){
                list = Demo1.list(movieCategory.getOtherId().replace("okzy_",""), page++);
                //pagecount = list.getPagecount();
                save(list.getList(),movieCategory);
            }
        }
        return 1;
    }

    @GetMapping("/list_all")
    public int listAll(){
        int page = 2535;
        com.bootx.vo.list.JsonRootBean list = Demo1.list(page++);
        int pagecount = list.getPagecount();
        List<com.bootx.vo.list.List> list1 = list.getList();
        save1(list1);
        while (page<pagecount){
            list = Demo1.list(page++);
            //pagecount = list.getPagecount();
            save1(list.getList());
        }
        return 1;
    }


    private void save(List<com.bootx.vo.list.List> list1,MovieCategory movieCategory){
        for (com.bootx.vo.list.List listItem:list1) {
            Movie1 movie1 = new Movie1();
            movie1.setEnglish(listItem.getVod_en());
            movie1.setPlayFrom(listItem.getVod_play_from());
            movie1.setRemarks(listItem.getVod_remarks());
            movie1.setTime(DateUtils.formatStringToDate(listItem.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setTitle(listItem.getVod_name());
            movie1.setVideoId("okzy_"+listItem.getVod_id());
            movie1.setMovieCategory(movieCategory);
            new Thread(()->{
                movie1Service.save(movie1);
            }).start();
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void save1(List<com.bootx.vo.list.List> list1){
        for (com.bootx.vo.list.List listItem:list1) {
            Boolean aBoolean = redisTemplate.hasKey("okzy_" + listItem.getVod_id());
            if(aBoolean){
                continue;
            }

            Movie1 movie1 = new Movie1();
            movie1.setEnglish(listItem.getVod_en());
            movie1.setPlayFrom(listItem.getVod_play_from());
            movie1.setRemarks(listItem.getVod_remarks());
            movie1.setTime(DateUtils.formatStringToDate(listItem.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setUpdateTime(DateUtils.formatStringToDate(listItem.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setTitle(listItem.getVod_name());
            movie1.setVideoId("okzy_"+listItem.getVod_id());
            movie1.setMovieCategory(movieCategoryService.findByOtherId("okzy_"+listItem.getType_id()));
            new Thread(()->{
                movie1Service.save(movie1);
            }).start();
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
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

    @GetMapping("/cache/remove")
    public String cacheClear() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        List<Map<String,Object>> list = new ArrayList<>();
        String [] cacheNames = cacheManager.getCacheNames();
        for (String cacheName:cacheNames) {
            cacheManager.getCache(cacheName).removeAll();
        }
        return "abc";
    }

    @GetMapping("/cache/add")
    public String cacheAdd() {
        List<Movie1> movie1s = movie1Service.findAll();
        for (Movie1 movie1:movie1s) {
            redisTemplate.opsForValue().set(movie1.getVideoId(),movie1.getId());
        }
        return "abc";
    }

    @GetMapping("/detail")
    public String detail() throws InterruptedException {
        for (Long id=0L;id<100000;id++){
            final Movie1[] movie1 = {movie1Service.find(id)};
            if(movie1!=null&&movie1[0] !=null){
               new Thread(()->{
                   List<Data> detail = Demo1.detail(movie1[0].getVideoId().replaceAll("okzy_", ""));
                   if(detail!=null&&detail.size()!=0){
                       Data data = detail.get(0);
                       movie1[0] = copy(movie1[0],data);
                       movie1Service.update(movie1[0]);
                   }
               }).start();
            }
            Thread.sleep(10);
        }
        return "abc";
    }

    private Movie1 copy(Movie1 movie1, Data data) {
        movie1.setEnglish(data.getVod_en());
        movie1.setActor(data.getVod_actor());
        movie1.setDirector(data.getVod_director());
        movie1.setArea(data.getVod_area());
        movie1.setAddTime(new Date(data.getVod_time_add()*1000));
        movie1.setContent(data.getVod_content());
        movie1.setLang(data.getVod_lang());
        movie1.setPic(data.getVod_pic());
        movie1.setSub(data.getVod_sub());
        movie1.setYear(data.getVod_year());
        movie1.setScore(data.getVod_score());
        movie1.setIsShow(true);
        String vod_play_url = data.getVod_play_url();
        movie1.setPlayUrls(parsePlayUrl(vod_play_url,movie1));
        String vod_down_url = data.getVod_down_url();
        movie1.setDownloadUrls(parseDownloadUrl(vod_down_url,movie1));
        return movie1;
    }

    private Set<DownloadUrl> parseDownloadUrl(String vod_down_url, Movie1 movie1) {

        List<DownloadUrl> downloadUrls = new ArrayList<>();
        String[] playUrlStrArray = vod_down_url.split("@@@@@@@@@");
        for (int i=0;i<playUrlStrArray.length;i++){
            DownloadUrl playUrl = new DownloadUrl();
            playUrl.setTitle("线路"+(i+1));
            playUrl.setMovie1(movie1);
            String playUrlStr = playUrlStrArray[i];
            String[] playUrlDetails = playUrlStr.split("#");
            List<String> urls = Arrays.asList(playUrlDetails);
            playUrl.setUrls(urls);
            downloadUrls.add(playUrl);
        }
        return new HashSet<>(downloadUrls);

    }

    private Set<PlayUrl> parsePlayUrl(String vodPlayUrl,Movie1 movie1){
        List<PlayUrl> playUrls = new ArrayList<>();
        String[] playUrlStrArray = vodPlayUrl.split("@@@@@@@@@");
        for (int i=0;i<playUrlStrArray.length;i++){
            PlayUrl playUrl = new PlayUrl();
            playUrl.setTitle("线路"+(i+1));
            playUrl.setMovie1(movie1);
            playUrl.setIsEnabled(true);
            String playUrlStr = playUrlStrArray[i];
            String[] playUrlDetails = playUrlStr.split("#");
            List<String> urls = Arrays.asList(playUrlDetails);
            playUrl.setUrls(urls);
            playUrls.add(playUrl);
        }
        return new HashSet<>(playUrls);
    }
}
