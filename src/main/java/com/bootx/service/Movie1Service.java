
package com.bootx.service;

import com.bootx.entity.Movie1;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface Movie1Service extends BaseService<Movie1,Long> {

    Movie1 findByVideoId(String videoId);

    Movie1 findByTitle(String title);

    void sync();

    Movie1 find1(Long id);
}