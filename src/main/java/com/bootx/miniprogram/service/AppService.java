
package com.bootx.miniprogram.service;

import com.bootx.miniprogram.entity.App;
import com.bootx.service.BaseService;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface AppService extends BaseService<App,Long> {

    App findByCodeAndSecret(String code,String secret);
}