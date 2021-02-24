
package com.bootx.service.impl;

import com.bootx.dao.PlayUrlDao;
import com.bootx.entity.Movie1;
import com.bootx.entity.PlayUrl;
import com.bootx.service.PlayUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class PlayUrlServiceImpl extends BaseServiceImpl<PlayUrl, Long> implements PlayUrlService {

    @Resource
    private PlayUrlDao playUrlDao;

    @Override
    public List<PlayUrl> findListByMovie(Movie1 movie1) {
        return playUrlDao.findListByMovie(movie1);
    }
}