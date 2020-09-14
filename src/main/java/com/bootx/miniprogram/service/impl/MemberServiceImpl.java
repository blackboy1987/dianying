
package com.bootx.miniprogram.service.impl;

import com.bootx.miniprogram.dao.MemberDao;
import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;
import com.bootx.miniprogram.service.MemberRankService;
import com.bootx.miniprogram.service.MemberService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberRankService memberRankService;

	@Override
	public Member find(String openId, App app) {
		return memberDao.find(openId,app);
	}

	@Override
	public Member create(String openId, App app) {
		Member member = memberDao.find("openId",openId);
		if(member==null){
			member = new Member();
			member.setOpenId(openId);
			member.setApp(app);
			member.setMemberRank(memberRankService.findDefault());
			return super.save(member);
		}
		return member;

	}
}