
package com.bootx.miniprogram.service;

import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;
import com.bootx.service.BaseService;

import java.util.Map;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface MemberService extends BaseService<Member,Long> {

    Member find(String openId, App app);

    Member create(Map<String,String > map, App app);

    Member findByUserTokenAndApp(String userToken, App app);


    Map<String,Object> getData(Member member);
}