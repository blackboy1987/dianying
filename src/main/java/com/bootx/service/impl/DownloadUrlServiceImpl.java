
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.DownloadUrlDao;
import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;
import com.bootx.service.DownloadUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
        return null;
    }

    @Override
    public Map<String, Object> findJdbc(Long id) {
        return null;
    }
}