package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ApiConfigDao;
import com.bootx.entity.APIConfig;
import com.bootx.service.APIConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class APIConfigServiceImpl extends BaseServiceImpl<APIConfig,Long> implements APIConfigService {


    @Autowired
    private ApiConfigDao apiConfigDao;
    

    @Override
    public APIConfig findByApiKey(String apiKey) {
        return apiConfigDao.find("apiKey",apiKey);
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
