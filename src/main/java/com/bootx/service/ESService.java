
package com.bootx.service;

import com.bootx.vo.es.MovieES;

import java.io.IOException;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface ESService{

    boolean put(MovieES movieES) throws IOException;
}