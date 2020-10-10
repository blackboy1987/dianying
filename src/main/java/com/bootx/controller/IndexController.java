package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.entity.SiteInfo;
import com.bootx.service.SiteInfoService;
import com.bootx.service.api.IndexService;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping
public class IndexController {


    @Autowired
    private IndexService indexService;

    @Autowired
    private SiteInfoService siteInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/")
    public int index(){
        updateResource();
        System.out.println("11111111111111");
        return 1/0;
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


    @GetMapping("/es")
    public int es() throws IOException {
        System.out.println("222222222222222222");
        indexService.es();
        return 1/0;
    }




    private void updateResource() {
        int page = 1;
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        JsonRootBean jsonRootBean = Demo1.list(page);
        int pagecount = jsonRootBean.getPagecount();
        if(!jsonRootBean.getData().isEmpty()){
            for (com.bootx.vo.okzy.com.jsoncn.pojo.Data data:jsonRootBean.getData()) {
                threadPool.submit(()->{
                    Movie movie = indexService.save(data);
                });
            }
        }
        if(page<pagecount){
            for (int i=page+1;i<=pagecount;i++) {
                jsonRootBean = Demo1.list(page);
                if(!jsonRootBean.getData().isEmpty()){
                    for (Data data:jsonRootBean.getData()) {
                        threadPool.submit(()->{
                            Movie movie = indexService.save(data);
                        });
                    }
                }else{
                    break;
                }
            }
        }
    }

    @GetMapping("/cache/remove")
    public String cacheClear() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        return "abc";
    }
}
