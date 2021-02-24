
package com.bootx.service;

import com.bootx.entity.Movie1;
import com.bootx.entity.PlayUrl;

import java.util.List;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface PlayUrlService extends BaseService<PlayUrl,Long> {
    List<PlayUrl> findListByMovie(Movie1 movie1);
}