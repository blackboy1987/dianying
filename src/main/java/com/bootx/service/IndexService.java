
package com.bootx.service;

import com.bootx.entity.MovieCategory;

import java.util.List;
import java.util.Map;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface IndexService{
    List<Map<String,Object>> movies(String type, MovieCategory movieCategory, Integer pageNumber, Integer count);

    List<Map<String, Object>> categories();
    Map<String,Object> detail(Long id);


    void init();
}