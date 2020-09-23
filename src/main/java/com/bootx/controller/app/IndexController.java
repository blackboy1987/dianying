package com.bootx.controller.app;

import com.bootx.common.Result;
import com.bootx.entity.MovieCategory;
import com.bootx.service.MovieCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("appIndexController")
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/index")
    public Result index(Long categoryId){
        Map<String,Object> data = new HashMap<>();
        if(categoryId!=null){
            MovieCategory movieCategory = movieCategoryService.find(categoryId);
            if(movieCategory!=null){
                return Result.success(movies(null,movieCategory,1));
            }
            return Result.success(Collections.emptyList());
        }else {
            data.put("categories",categories());
            data.put("hotMovies",movies("hotMovies",null,null));
            data.put("hottv",movies("hottv",null,null));
            data.put("new",movies("new",null,null));
            return Result.success(data);
        }
    }

    private List<Map<String,Object>> movies(String type,MovieCategory movieCategory,Integer pageNumber){
        StringBuilder sql = new StringBuilder("select vod_name,vod_pic,vod_id,vod_lang,vod_remarks from movie.Movie where 1=1");
        String orderBy = "";
        String limit = "";
        if(movieCategory!=null){
            sql.append(" and type_id="+movieCategory.getCategoryId());
        }

        if(StringUtils.equals(type,"hotMovies")){
            sql.append(" and type_name='电影'");
            orderBy=" order by vod_score desc";
            limit = " limit 10";
        }else if(StringUtils.equals(type,"hottv")){
            sql.append(" and type_name='连续剧'");
            orderBy=" order by vod_score desc";
            limit = " limit 10";
        }else if(StringUtils.equals(type,"new")){
            orderBy=" order by vod_score desc";
            limit = " limit 10";
        }
        if(StringUtils.isEmpty(orderBy)){
            orderBy=" order by vod_time desc";
        }
        if(StringUtils.isEmpty(limit)&&pageNumber!=null&&pageNumber>0){
            limit = " limit "+(pageNumber-1)*10+", 10";
        }

        sql.append(orderBy);
        sql.append(limit);
        System.out.println(sql.toString());
        return jdbcTemplate.queryForList(sql.toString());
    }

    /**
     * 分类
     * @return
     */
    private List<Map<String,Object>> categories(){
        List<Map<String,Object>> categories = new ArrayList<>();
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        for (MovieCategory movieCategory:movieCategories) {
            Map<String,Object> mo = new HashMap<>();
            mo.put("id",movieCategory.getId());
            mo.put("name",movieCategory.getName());
            categories.add(mo);
        }

        return categories;
    }

}
