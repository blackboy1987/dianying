package com.bootx.controller;

import com.bootx.Demo1;
import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.api.IndexService;
import com.bootx.vo.okzy.com.jsoncn.pojo.Category;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/index3")
public class Index3Controller {

    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/category")
    public String category(){
        JsonRootBean jsonRootBean = Demo1.category();
        for (Category category:jsonRootBean.getCategory()) {
            MovieCategory movieCategory = movieCategoryService.findByName(category.getType_name());
            if(movieCategory==null){
                movieCategory = new MovieCategory();
            }
            movieCategory.setName(category.getType_name());
            movieCategory.setOtherId("okzy_"+category.getType_id());
            if(movieCategory.isNew()){
                movieCategoryService.save(movieCategory);
            }else{
                movieCategoryService.update(movieCategory);
            }
        }
        return "ok";
    }


    @GetMapping("/detail")
    public String detail() throws Exception{
        // 14:03:30
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        Integer pagecount = Demo1.category().getPagecount();
        System.out.println(pagecount);
        for (int page=1;page<=pagecount;page++) {
            List<Data> list = Demo1.detail(page);
            for (Data data:list) {
                threadPool.submit(()->{
                    Movie movie = indexService.save(data);
                });
            }
            System.out.println(new Date()+"page========================================"+page);
        }

        threadPool.shutdown();
        return "jsonRootBean";
    }

}
