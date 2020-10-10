
package com.bootx.service;

import com.bootx.entity.App;
import com.bootx.entity.SiteInfo;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface SiteInfoService extends BaseService<SiteInfo,Long> {

    SiteInfo find(App app);
}