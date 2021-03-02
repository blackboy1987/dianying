
package com.bootx.member.dao.impl;

import com.bootx.dao.impl.BaseDaoImpl;
import com.bootx.member.dao.MemberDao;
import com.bootx.entity.App;
import com.bootx.member.entity.Member;
import org.apache.commons.lang3.StringUtils;
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
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

    @Override
    public Member find(String openId, App app) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (app == null || StringUtils.isEmpty(openId)) {
            return null;
        }
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("openId"), openId));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("app"), app));
        criteriaQuery.where(restrictions);
        try {
            return super.findList(criteriaQuery).get(0);
        }catch (Exception e){
            return null;
        }
    }
}