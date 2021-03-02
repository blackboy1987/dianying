
package com.bootx.member.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.member.dao.PointLogDao;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.PointLog;
import com.bootx.member.service.PointLogService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Service - 积分记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Service
public class PointLogServiceImpl extends BaseServiceImpl<PointLog, Long> implements PointLogService {

	@Autowired
	private PointLogDao pointLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<PointLog> findPage(Member member, Pageable pageable) {
		return pointLogDao.findPage(member, pageable);
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