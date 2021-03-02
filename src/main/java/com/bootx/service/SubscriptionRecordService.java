
package com.bootx.service;

import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.member.entity.Member;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface SubscriptionRecordService extends BaseService<SubscriptionRecord,Long> {

    Long count(App app, Member member, SubscriptionTemplate subscriptionTemplate);

}