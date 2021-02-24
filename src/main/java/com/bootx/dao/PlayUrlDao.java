
package com.bootx.dao;

import com.bootx.entity.Movie1;
import com.bootx.entity.PlayUrl;

import java.util.List;

/**
 * Dao - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
public interface PlayUrlDao extends BaseDao<PlayUrl, Long> {

    List<PlayUrl> findListByMovie(Movie1 movie1);
}