package com.bootx.service.impl;

import com.bootx.dao.ApiConfigDao;
import com.bootx.entity.APIConfig;
import com.bootx.service.APIConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class APIConfigServiceImpl extends BaseServiceImpl<APIConfig,Long> implements APIConfigService {


    @Autowired
    private ApiConfigDao apiConfigDao;
    

    @Override
    public APIConfig findByApiKey(String apiKey) {
        return apiConfigDao.find("apiKey",apiKey);
    }


}
