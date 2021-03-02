
package com.bootx.service.impl;

import com.bootx.dao.SubscriptionRecordDao;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.member.entity.Member;
import com.bootx.service.SubscriptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class SubscriptionRecordServiceImpl extends BaseServiceImpl<SubscriptionRecord, Long> implements SubscriptionRecordService {

    @Autowired
    private SubscriptionRecordDao subscriptionRecordDao;


    @Override
    public Long count(App app, Member member, SubscriptionTemplate subscriptionTemplate) {
        return subscriptionRecordDao.count(app,member,subscriptionTemplate);
    }
}