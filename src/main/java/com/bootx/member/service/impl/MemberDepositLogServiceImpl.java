
package com.bootx.member.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.member.dao.MemberDepositLogDao;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.MemberDepositLog;
import com.bootx.member.service.MemberDepositLogService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * Service - 会员预存款记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Service
public class MemberDepositLogServiceImpl extends BaseServiceImpl<MemberDepositLog, Long> implements MemberDepositLogService {

	@Autowired
	private MemberDepositLogDao memberDepositLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<MemberDepositLog> findPage(Member member, Pageable pageable) {
		return memberDepositLogDao.findPage(member, pageable);
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