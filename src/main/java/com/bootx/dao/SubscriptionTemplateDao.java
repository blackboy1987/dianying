package com.bootx.dao;

import com.bootx.entity.App;
import com.bootx.entity.SubscriptionTemplate;

import java.util.List;

public interface SubscriptionTemplateDao extends BaseDao<SubscriptionTemplate, Long> {

    boolean exists(App app, String templateId);

    List<SubscriptionTemplate> findListByAppAndSubscriptionTemplateIds(App app, String[] tmpIds);
}
