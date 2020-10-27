package com.bootx.controller;

import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.util.JuHuiTV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/index4")
public class Index4Controller {

    @Autowired
    private MovieCategoryService movieCategoryService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/index")
    public String index(Integer page) throws Exception{
        if(page==null|| page<=0){
            return "error";
        }
        List<Movie> movies = JuHuiTV.search(page);
        for (Movie movie:movies) {
            movie.setIsShow(true);
            MovieCategory movieCategory = movieCategoryService.findByName(movie.getMovieCategory().getName());
            if(movieCategory==null){
                movieCategory = movie.getMovieCategory();
                movieCategory.setIsShow(true);
                movieCategory = movieCategoryService.save(movieCategory);
            }
            Movie movie1 = movieService.findByTitle(movie.getTitle());
            if(movie1==null){
                movie.setMovieCategory(movieCategory);
                movieService.save(movie);
            }else{
                Set<PlayUrl> playUrls = movie.getPlayUrls();
                for (PlayUrl playUrl:playUrls) {
                    playUrl.setMovie(movie1);
                }
                movie1.getPlayUrls().addAll(playUrls);
                movieService.update(movie1);
            }



        }
        return "ok";
    }

}
