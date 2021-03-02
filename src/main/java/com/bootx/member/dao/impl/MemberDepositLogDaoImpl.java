/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.bootx.member.dao.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.impl.BaseDaoImpl;
import com.bootx.member.dao.MemberDepositLogDao;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.MemberDepositLog;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Dao - 会员预存款记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Repository
public class MemberDepositLogDaoImpl extends BaseDaoImpl<MemberDepositLog, Long> implements MemberDepositLogDao {

	public Page<MemberDepositLog> findPage(Member member, Pageable pageable) {
		if (member == null) {
			return Page.emptyPage(pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberDepositLog> criteriaQuery = criteriaBuilder.createQuery(MemberDepositLog.class);
		Root<MemberDepositLog> root = criteriaQuery.from(MemberDepositLog.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
		return super.findPage(criteriaQuery, pageable);
	}

}