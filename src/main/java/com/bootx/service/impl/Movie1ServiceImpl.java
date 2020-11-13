
package com.bootx.service.impl;

import com.bootx.dao.Movie1Dao;
import com.bootx.entity.Movie1;
import com.bootx.service.Movie1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class Movie1ServiceImpl extends BaseServiceImpl<Movie1, Long> implements Movie1Service {

    @Autowired
    private Movie1Dao movie1Dao;

    @Override
     @Cacheable(value = "movie",key = "#id")
    public Movie1 find(Long id) {
        return super.find(id);
    }


    @Override
    public Movie1 find1(Long id) {
        return super.find(id);
    }

    @Override
    public Movie1 findByVideoId(String videoId) {
        return movie1Dao.find("videoId",videoId);
    }

    @Override
    public Movie1 findByTitle(String title) {
        return movie1Dao.find("title",title);
    }

    @Override
    public void sync() {

    }

    @Override
    public Movie1 save(Movie1 movie1) {
        return super.save(movie1);
    }
}