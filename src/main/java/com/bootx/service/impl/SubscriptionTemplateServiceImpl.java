
package com.bootx.service.impl;

import com.bootx.dao.SubscriptionTemplateDao;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.service.SubscriptionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class SubscriptionTemplateServiceImpl extends BaseServiceImpl<SubscriptionTemplate, Long> implements SubscriptionTemplateService {

    @Autowired
    private SubscriptionTemplateDao subscriptionTemplateDao;

    @Override
    public boolean exists(App app, String templateId) {
        return subscriptionTemplateDao.exists(app,templateId);
    }

    @Override
    public List<SubscriptionTemplate> findListByAppAndSubscriptionTemplateIds(App app, String[] tmpIds) {
        return subscriptionTemplateDao.findListByAppAndSubscriptionTemplateIds(app,tmpIds);
    }
}