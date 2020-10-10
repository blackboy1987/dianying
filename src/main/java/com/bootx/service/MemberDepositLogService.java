
package com.bootx.service;


import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.Member;
import com.bootx.entity.MemberDepositLog;

/**
 * Service - 会员预存款记录
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface MemberDepositLogService extends BaseService<MemberDepositLog, Long> {

	/**
	 * 查找会员预存款记录分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 会员预存款记录分页
	 */
	Page<MemberDepositLog> findPage(Member member, Pageable pageable);

}