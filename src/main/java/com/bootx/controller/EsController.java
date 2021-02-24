package com.bootx.controller;

import com.bootx.entity.Movie1;
import com.bootx.es.service.EsMovieService;
import com.bootx.service.Movie1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/es")
public class EsController {


    @Resource
    private EsMovieService esMovieService;

    @Resource
    private Movie1Service movie1Service;


    @GetMapping("/flush")
    private String flushEs(){
        List<Movie1> all = movie1Service.findAll();
        for (Movie1 movie1:all) {
            esMovieService.add(movie1);
        }
        return "ok";
    }
}
