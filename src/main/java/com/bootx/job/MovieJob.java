package com.bootx.job;

import com.bootx.service.Movie1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MovieJob {
    @Autowired
    private Movie1Service movie1Service;

    /**
     * 每天12点10分，进行更新
     */
    @Scheduled(cron = "30 35 10 * * ?")
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println(new Date()+":定时任务启动============================================");
        movie1Service.sync();
        System.out.println(new Date()+":定时任务结束=============================================:"+(System.currentTimeMillis()-start)/1000);
    }
}
