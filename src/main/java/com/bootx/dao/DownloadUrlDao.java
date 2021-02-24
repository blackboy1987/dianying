
package com.bootx.dao;

import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;

import java.util.List;

/**
 * Dao - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
public interface DownloadUrlDao extends BaseDao<DownloadUrl, Long> {

    List<DownloadUrl> findListByMovie(Movie1 movie1);
}