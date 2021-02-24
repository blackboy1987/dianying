
package com.bootx.service;

import com.bootx.entity.Movie1;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;

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

    Movie1 copy(Movie1 movie1, Data data);
}