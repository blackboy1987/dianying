
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.PointLogDao;
import com.bootx.entity.Member;
import com.bootx.entity.PointLog;
import com.bootx.service.PointLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}