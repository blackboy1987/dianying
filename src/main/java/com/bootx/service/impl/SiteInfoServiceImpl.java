
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.SiteInfoDao;
import com.bootx.entity.App;
import com.bootx.entity.SiteInfo;
import com.bootx.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class SiteInfoServiceImpl extends BaseServiceImpl<SiteInfo, Long> implements SiteInfoService {

    @Autowired
    private SiteInfoDao siteInfoDao;

    @Override
    public SiteInfo find(App app) {
        return siteInfoDao.find("appId",app.getId());
    }

    @Override
    public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
        return null;
    }

    @Override
    public Map<String, Object> findJdbc(Long id) {
        return jdbcTemplate.queryForMap("select * from siteInfo where appId="+id);
    }
}