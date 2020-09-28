
package com.bootx.service;

import com.bootx.entity.Movie;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MovieService extends BaseService<Movie,Long> {

    public Movie findByVideoId(String videoId);

    Movie findByTitle(String title);
}