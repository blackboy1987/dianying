
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.MemberDepositLogDao;
import com.bootx.entity.Member;
import com.bootx.entity.MemberDepositLog;
import com.bootx.service.MemberDepositLogService;
import org.elasticsearch.common.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Service - 会员预存款记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Service
public class MemberDepositLogServiceImpl extends BaseServiceImpl<MemberDepositLog, Long> implements MemberDepositLogService {

	@Inject
	private MemberDepositLogDao memberDepositLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<MemberDepositLog> findPage(Member member, Pageable pageable) {
		return memberDepositLogDao.findPage(member, pageable);
	}

}