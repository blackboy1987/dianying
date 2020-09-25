package com.bootx.controller.app;

import com.bootx.common.Result;
import com.bootx.service.IndexService;
import com.bootx.service.MovieCategoryService;
import com.bootx.util.EhCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("appIndexController")
@RequestMapping("/api")
public class IndexController {


    @Autowired
    private IndexService indexService;

    @Autowired
    private MovieCategoryService movieCategoryService;

    @GetMapping("/list")
    public Result list(Long categoryId,Integer pageNumber){
        Map<String,Object> data = new HashMap<>();
        if(pageNumber==null||pageNumber<=0){
            pageNumber = 1;
        }
        if(categoryId==0){
            data.put("hotMovies",indexService.movies("hotMovies",null,null,18));
            data.put("hottv",indexService.movies("hottv",null,null,18));
            data.put("new",indexService.movies("new",null,null,18));
            return Result.success(data);
        }
        return Result.success(indexService.movies(null,movieCategoryService.find(categoryId),pageNumber,18));

    }

    /**
     * 分类
     * @return
     */
    @GetMapping("/categories")
    public Result categories(){
        return Result.success(indexService.categories());
    }


    /**
     * 分类
     * @return
     */
    @GetMapping("/info")
    public Result detail(Long id){
        return Result.success(indexService.detail(id));
    }

    /**
     * 分类
     * @return
     */
    @GetMapping("/cache")
    public Result cache(){
        return Result.success(EhCacheUtils.getCacheValue());
    }

    /**
     * 分类
     * @return
     */
    @GetMapping("/remove")
    public Result cache(String cacheName,String cacheKey){
        return Result.success(EhCacheUtils.removeCache(cacheName,cacheKey));
    }
}
