
package com.bootx.dao.impl;

import com.bootx.dao.SubscriptionRecordDao;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.member.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Dao - 插件配置
 * 
 * @author blackboy
 * @version 1.0
 */
@Repository
public class SubscriptionRecordDaoImpl extends BaseDaoImpl<SubscriptionRecord, Long> implements SubscriptionRecordDao {

    @Override
    public Long count(App app, Member member, SubscriptionTemplate subscriptionTemplate) {
        if(app==null|| member==null|| subscriptionTemplate==null){
            return 0L;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionRecord> criteriaQuery = criteriaBuilder.createQuery(SubscriptionRecord.class);
        Root<SubscriptionRecord> root = criteriaQuery.from(SubscriptionRecord.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("app"), app));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("subscriptionTemplate"), subscriptionTemplate));
        criteriaQuery.where(restrictions);
        return super.count(criteriaQuery, null);
    }
}