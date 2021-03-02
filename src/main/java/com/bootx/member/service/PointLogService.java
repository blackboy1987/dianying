
package com.bootx.member.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.PointLog;
import com.bootx.service.BaseService;

/**
 * Service - 积分记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface PointLogService extends BaseService<PointLog, Long> {

	/**
	 * 查找积分记录分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 积分记录分页
	 */
	Page<PointLog> findPage(Member member, Pageable pageable);

}