
package com.bootx.service;

import com.bootx.entity.App;
import com.bootx.entity.SubscriptionTemplate;

import java.util.List;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface SubscriptionTemplateService extends BaseService<SubscriptionTemplate,Long> {

    boolean exists(App app,String templateId);

    List<SubscriptionTemplate> findListByAppAndSubscriptionTemplateIds(App app, String[] tmpIds);
}