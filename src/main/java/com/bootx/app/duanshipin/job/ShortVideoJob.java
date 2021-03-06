package com.bootx.app.duanshipin.job;

import com.bootx.app.duanshipin.service.ShortVideoService;
import com.bootx.service.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
public class ShortVideoJob {

    @Resource
    private ShortVideoService shortVideoService;

    @Resource
    private RedisService redisService;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void run (){
        Integer pageInt = 1;
        Integer count = 9;
        try{
            String page = redisService.get("page");
            pageInt = Integer.parseInt(page);
        }catch (Exception e){

        }
        long start = System.currentTimeMillis();
        System.out.println(pageInt+"===============start================="+start);
        shortVideoService.sync(pageInt,count+pageInt);
        redisService.set("page",(pageInt+count)+"");
        System.out.println(pageInt+"===============end==================="+(System.currentTimeMillis()-start)/1000);
    }
}
