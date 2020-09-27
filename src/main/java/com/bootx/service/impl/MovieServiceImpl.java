
package com.bootx.service.impl;

import com.bootx.dao.MovieDao;
import com.bootx.entity.Movie;
import com.bootx.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long> implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie findByVideoId(String videoId) {
        return movieDao.find("videoId",videoId);
    }
}