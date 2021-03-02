package com.bootx.dao;

import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.member.entity.Member;

public interface SubscriptionRecordDao extends BaseDao<SubscriptionRecord, Long> {

    Long count(App app, Member member, SubscriptionTemplate subscriptionTemplate);

}
