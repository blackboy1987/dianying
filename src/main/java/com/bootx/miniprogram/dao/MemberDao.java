
package com.bootx.miniprogram.dao;

import com.bootx.dao.BaseDao;
import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;

/**
 * Dao - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MemberDao extends BaseDao<Member, Long> {

    Member find(String openId, App app);
}