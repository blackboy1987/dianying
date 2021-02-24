
package com.bootx.service.impl;

import com.bootx.dao.DownloadUrlDao;
import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;
import com.bootx.service.DownloadUrlService;
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
public class DownloadUrlServiceImpl extends BaseServiceImpl<DownloadUrl, Long> implements DownloadUrlService {

    @Resource
    private DownloadUrlDao downloadUrlDao;

    @Override
    public List<DownloadUrl> findListByMovie(Movie1 movie1) {
        return downloadUrlDao.findListByMovie(movie1);
    }
}