package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.service.api.IndexService;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping
public class IndexController {


    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public int index(){
        updateResource();
        return 1/0;
    }


    @GetMapping("/es")
    public int es() throws IOException {
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
}
