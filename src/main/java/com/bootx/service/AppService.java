
package com.bootx.service;

import com.bootx.entity.App;

import javax.servlet.http.HttpServletRequest;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface AppService extends BaseService<App,Long> {

    App findByAppCode(String appCode);

    boolean exist(String appCode, String appSecret);

    App get(HttpServletRequest request);
}