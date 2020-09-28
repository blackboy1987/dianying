package com.bootx.controller.admin;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.MovieCategory;
import com.bootx.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminMovieCategoryController")
@RequestMapping("/admin/movie_category")
public class MovieCategoryController {

    @Autowired
    private MovieCategoryService movieCategoryService;

    @GetMapping("/list")
    public Result list(Pageable pageable,String title){
        Page<MovieCategory> page = movieCategoryService.findPage(pageable);
        List<Map<String,Object>> list = new ArrayList<>();
        for (MovieCategory movie:page.getContent()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",movie.getId());
            map.put("name",movie.getName());
            list.add(map);
        }
        return Result.success(new Page<>(list,page.getTotal(),pageable));
    }
}
