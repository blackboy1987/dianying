package com.bootx.controller.api.v2;

import com.bootx.entity.Movie1;
import com.bootx.es.service.EsMovieService;
import com.bootx.service.Movie1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("apiV2EsController")
@RequestMapping("/api/v2/es")
public class EsController {

    @Resource
    private Movie1Service movie1Service;
    @Resource
    private EsMovieService esMovieService;

    @GetMapping("/flush")
    public String flush(){
        for (Long i = 1L; i < 100000; i++) {
           try {
               Movie1 movie1 = movie1Service.find(i);
               System.out.println("================================="+i);
               if(movie1!=null){
                   System.out.println(movie1.getId());
                   esMovieService.add(movie1);
               }
           }catch (Exception e){
               e.printStackTrace();
           }

        }
        return "ok";
    }

}
