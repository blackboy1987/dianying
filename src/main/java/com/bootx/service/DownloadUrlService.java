
package com.bootx.service;

import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;

import java.util.List;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface DownloadUrlService extends BaseService<DownloadUrl,Long> {
    List<DownloadUrl> findListByMovie(Movie1 movie1);
}