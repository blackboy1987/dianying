
package com.bootx.member.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;
import com.bootx.member.dao.MemberRankDao;
import com.bootx.member.entity.MemberRank;
import com.bootx.member.service.MemberRankService;
import com.bootx.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Map;


/**
 * Service - 会员等级
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Service
public class MemberRankServiceImpl extends BaseServiceImpl<MemberRank, Long> implements MemberRankService {

	@Autowired
	private MemberRankDao memberRankDao;

	@Override
	@Transactional(readOnly = true)
	public boolean amountExists(BigDecimal amount, App app) {
		return memberRankDao.exists("amount", amount);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean amountUnique(Long id, BigDecimal amount, App app) {
		return memberRankDao.unique(id, "amount", amount);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberRank findDefault(App app) {
		return memberRankDao.findDefault(app);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberRank findByAmount(BigDecimal amount, App app) {
		return memberRankDao.findByAmount(amount,app);
	}

	@Override
	@Transactional
	public MemberRank save(MemberRank memberRank) {
		Assert.notNull(memberRank,"");

		if (BooleanUtils.isTrue(memberRank.getIsDefault())) {
			memberRankDao.clearDefault(memberRank.getApp());
		}
		return super.save(memberRank);
	}

	@Override
	@Transactional
	public MemberRank update(MemberRank memberRank) {
		Assert.notNull(memberRank,"");

		MemberRank pMemberRank = super.update(memberRank);
		if (BooleanUtils.isTrue(pMemberRank.getIsDefault())) {
			memberRankDao.clearDefault(pMemberRank, memberRank.getApp());
		}
		return pMemberRank;
	}

	@Override
	public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
		return null;
	}

	@Override
	public Map<String, Object> findJdbc(Long id) {
		return null;
	}

}