package com.bootx.service;

import com.bootx.entity.APIConfig;

public interface APIConfigService extends BaseService<APIConfig,Long> {
    APIConfig findByApiKey(String apiKey);

}