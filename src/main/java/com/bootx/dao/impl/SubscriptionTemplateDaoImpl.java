
package com.bootx.dao.impl;

import com.bootx.dao.SubscriptionTemplateDao;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Dao - 插件配置
 * 
 * @author blackboy
 * @version 1.0
 */
@Repository
public class SubscriptionTemplateDaoImpl extends BaseDaoImpl<SubscriptionTemplate, Long> implements SubscriptionTemplateDao {

    @Override
    public boolean exists(App app, String templateId) {

        if(app==null|| StringUtils.isBlank(templateId)){
            return false;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionTemplate> criteriaQuery = criteriaBuilder.createQuery(SubscriptionTemplate.class);
        Root<SubscriptionTemplate> root = criteriaQuery.from(SubscriptionTemplate.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("app"), app));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("templateId"), templateId));
        criteriaQuery.where(restrictions);
        return super.count(criteriaQuery, null)>0;
    }

    @Override
    public List<SubscriptionTemplate> findListByAppAndSubscriptionTemplateIds(App app, String[] tmpIds) {
        if(app==null){
            return Collections.emptyList();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionTemplate> criteriaQuery = criteriaBuilder.createQuery(SubscriptionTemplate.class);
        Root<SubscriptionTemplate> root = criteriaQuery.from(SubscriptionTemplate.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("app"), app));

        CriteriaBuilder.In<List<String>> groupIdInClause = criteriaBuilder.in(root.get("templateId"));
        groupIdInClause.value(criteriaBuilder.literal(Arrays.asList(tmpIds)));

        restrictions = criteriaBuilder.and(restrictions, groupIdInClause);
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery);

    }
}