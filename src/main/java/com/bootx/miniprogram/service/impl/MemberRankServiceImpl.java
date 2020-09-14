
package com.bootx.miniprogram.service.impl;

import com.bootx.miniprogram.dao.MemberRankDao;
import com.bootx.miniprogram.entity.MemberRank;
import com.bootx.miniprogram.service.MemberRankService;
import com.bootx.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	public boolean nameExists(String name) {
		return memberRankDao.exists("name", name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean nameUnique(Long id, String name) {
		return memberRankDao.unique(id, "name", name);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberRank findDefault() {
		return memberRankDao.findDefault();
	}


	@Override
	@Transactional
	public MemberRank save(MemberRank memberRank) {
		Assert.notNull(memberRank,"");

		if (BooleanUtils.isTrue(memberRank.getIsDefault())) {
			memberRankDao.clearDefault();
		}
		return super.save(memberRank);
	}

	@Override
	@Transactional
	public MemberRank update(MemberRank memberRank) {
		Assert.notNull(memberRank,"");

		MemberRank pMemberRank = super.update(memberRank);
		if (BooleanUtils.isTrue(pMemberRank.getIsDefault())) {
			memberRankDao.clearDefault(pMemberRank);
		}
		return pMemberRank;
	}

}