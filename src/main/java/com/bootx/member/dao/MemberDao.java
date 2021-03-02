
package com.bootx.member.dao;

import com.bootx.dao.BaseDao;
import com.bootx.entity.App;
import com.bootx.member.entity.Member;

/**
 * Dao - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MemberDao extends BaseDao<Member, Long> {

    Member find(String openId, App app);
}