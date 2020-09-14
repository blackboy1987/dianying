
package com.bootx.miniprogram.service;

import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;
import com.bootx.service.BaseService;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MemberService extends BaseService<Member,Long> {

    Member find(String openId, App app);

    Member create(String openId,App app);
}