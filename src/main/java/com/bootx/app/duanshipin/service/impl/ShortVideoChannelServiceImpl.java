
package com.bootx.app.duanshipin.service.impl;

import com.bootx.app.duanshipin.dao.ShortVideoChannelDao;
import com.bootx.app.duanshipin.entity.ShortVideoChannel;
import com.bootx.app.duanshipin.service.ShortVideoChannelService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class ShortVideoChannelServiceImpl extends BaseServiceImpl<ShortVideoChannel, Long> implements ShortVideoChannelService {

    @Resource
    private ShortVideoChannelDao shortVideoChannelDao;
}