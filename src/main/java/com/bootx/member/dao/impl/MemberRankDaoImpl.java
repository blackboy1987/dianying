
package com.bootx.member.dao.impl;

import com.bootx.dao.impl.BaseDaoImpl;
import com.bootx.entity.App;
import com.bootx.member.dao.MemberRankDao;
import com.bootx.member.entity.MemberRank;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.NoResultException;
import java.math.BigDecimal;

/**
 * Dao - 会员等级
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Repository
public class MemberRankDaoImpl extends BaseDaoImpl<MemberRank, Long> implements MemberRankDao {

	@Override
	public MemberRank findDefault(App app) {
		try {
			String jpql = "select memberRank from MemberRank memberRank where memberRank.isDefault = true and memberRank.app = :app";
			return entityManager.createQuery(jpql, MemberRank.class).setParameter("app",app).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public MemberRank findByAmount(BigDecimal amount, App app) {
		String jpql = "select memberRank from MemberRank memberRank where memberRank.isSpecial = false and memberRank.amount <= :amount and memberRank.app = :app order by memberRank.amount desc";
		return entityManager.createQuery(jpql, MemberRank.class).setParameter("amount", amount).setParameter("app",app).setMaxResults(1).getSingleResult();
	}

	@Override
	public void clearDefault(App app) {
		String jpql = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true and memberRank.app = : app";
		entityManager.createQuery(jpql).setParameter("app",app).executeUpdate();
	}

	@Override
	public void clearDefault(MemberRank exclude, App app) {
		Assert.notNull(exclude);

		String jpql = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true and memberRank != :exclude and memberRank.app = : app";
		entityManager.createQuery(jpql).setParameter("exclude", exclude).setParameter("app",app).executeUpdate();
	}

}