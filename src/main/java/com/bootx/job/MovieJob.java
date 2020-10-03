package com.bootx.job;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.service.api.IndexService;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MovieJob {

    @Autowired
    private IndexService indexService;

    /**
     * 每天一点执行一次
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void run(){
        System.out.println("start");
        updateResource();
        System.out.println("end");
    }

    private void updateResource() {
        int page = 1;
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        JsonRootBean jsonRootBean = Demo1.list(page);
        int pagecount = jsonRootBean.getPagecount();
        if(!jsonRootBean.getData().isEmpty()){
            for (Data data:jsonRootBean.getData()) {
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
}
