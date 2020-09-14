
package com.bootx.miniprogram.service;

import com.bootx.miniprogram.entity.MemberRank;
import com.bootx.service.BaseService;

;

/**
 * Service - 会员等级
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface MemberRankService extends BaseService<MemberRank, Long> {

	/**
	 * 判断消费金额是否存在
	 * 
	 * @param name
	 *            消费金额
	 * @return 消费金额是否存在
	 */
	boolean nameExists(String name);

	/**
	 * 判断消费金额是否唯一
	 * 
	 * @param id
	 *            ID
	 * @param name
	 *            消费金额
	 * @return 消费金额是否唯一
	 */
	boolean nameUnique(Long id, String name);

	/**
	 * 查找默认会员等级
	 * 
	 * @return 默认会员等级，若不存在则返回null
	 */
	MemberRank findDefault();


}