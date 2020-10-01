package com.bootx.controller;

import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.service.MovieService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Index2Controller")
@RequestMapping("/index2")
public class Index2Controller {

    @Autowired
    private MovieService movieService;


    @GetMapping
    @JsonView(BaseEntity.ViewView.class)
    public Result index() {
        movieService.sync();
        return Result.success(null);
    }

}
