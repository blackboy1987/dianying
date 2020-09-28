package com.bootx.controller.admin;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.MovieTag;
import com.bootx.service.MovieTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminMovieTagController")
@RequestMapping("/admin/movie_tag")
public class MovieTagController {

    @Autowired
    private MovieTagService movieTagService;

    @GetMapping("/list")
    public Result list(Pageable pageable,String name){
        Page<MovieTag> page = movieTagService.findPage(pageable);
        List<Map<String,Object>> list = new ArrayList<>();
        for (MovieTag movieTag:page.getContent()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",movieTag.getId());
            map.put("name",movieTag.getName());
            list.add(map);
        }
        return Result.success(new Page<>(list,page.getTotal(),pageable));
    }
}
