
package com.bootx.member.dao.impl;

import com.bootx.dao.impl.BaseDaoImpl;
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
	public MemberRank findDefault() {
		try {
			String jpql = "select memberRank from MemberRank memberRank where memberRank.isDefault = true";
			return entityManager.createQuery(jpql, MemberRank.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public MemberRank findByAmount(BigDecimal amount) {
		String jpql = "select memberRank from MemberRank memberRank where memberRank.isSpecial = false and memberRank.amount <= :amount order by memberRank.amount desc";
		return entityManager.createQuery(jpql, MemberRank.class).setParameter("amount", amount).setMaxResults(1).getSingleResult();
	}

	@Override
	public void clearDefault() {
		String jpql = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true";
		entityManager.createQuery(jpql).executeUpdate();
	}

	@Override
	public void clearDefault(MemberRank exclude) {
		Assert.notNull(exclude);

		String jpql = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true and memberRank != :exclude";
		entityManager.createQuery(jpql).setParameter("exclude", exclude).executeUpdate();
	}

}