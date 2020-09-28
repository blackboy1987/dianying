package com.bootx.controller.admin;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.Movie;
import com.bootx.service.MovieService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("adminMovieController")
@RequestMapping("/admin/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    public Result list(Pageable pageable,String title){
        Page<Movie> page = movieService.findPage(pageable);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Movie movie:page.getContent()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",movie.getId());
            map.put("title",movie.getTitle());
            list.add(map);
        }
        return Result.success(new Page<>(list,page.getTotal(),pageable));
    }


    @GetMapping("/sync")
    public Result sync(Long id){
        String url = "https://bg.zqbkk.cn/app/index.php?i=2&t=0&v=1.0&from=wxapp&c=entry&a=wxapp&do=Search&m=sg_movie&sign=24fea3a05b103e687e2e1daead71f1fa";
        Movie movie = movieService.find(id);
        String keywords = movie.getTitle();
        Map<String,Object> params = new HashMap<>();
        params.put("key",keywords);
        String result = WebUtils.get(url,params);

        JsonRootBean jsonRootBean = JsonUtils.toObject(result,JsonRootBean.class);

        return Result.success(jsonRootBean);
    }
}
