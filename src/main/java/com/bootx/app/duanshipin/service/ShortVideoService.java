
package com.bootx.app.duanshipin.service;

import com.bootx.app.duanshipin.entity.ShortVideo;
import com.bootx.service.BaseService;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface ShortVideoService extends BaseService<ShortVideo,Long> {

    void sync(Integer start,Integer end);

    boolean exist(String videoId);
}