
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.MemberDao;
import com.bootx.dao.MemberDepositLogDao;
import com.bootx.dao.MemberRankDao;
import com.bootx.dao.PointLogDao;
import com.bootx.entity.*;
import com.bootx.service.MemberRankService;
import com.bootx.service.MemberService;
import com.bootx.util.DateUtils;
import com.bootx.util.JWTUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	private MemberDepositLogDao memberDepositLogDao;
	@Autowired
	private PointLogDao pointLogDao;
	@Autowired
	private MemberRankDao memberRankDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Member find(String openId, App app) {
		return memberDao.find(openId,app);
	}

	@Override
	public Member create(Map<String,String> map, App app) {
		String openId = map.get("openid");
		String unionid = map.get("unionid");
		String sessionKey = map.get("session_key");
		if(StringUtils.isEmpty(openId)){
			return null;
		}
		Map<String,Object> result = new HashMap<>();
		result.put("openId",openId);
		result.put("unionid",unionid);
		result.put("sessionKey",sessionKey);

		Member member = memberDao.find("openId",openId);
		if(member==null){
			member = new Member();
			member.setOpenId(openId);
			member.setUnionid(unionid);
			member.setSessionKey(sessionKey);
			member.setApp(app);
			member.setAmount(BigDecimal.ZERO);
			member.setBalance(BigDecimal.ZERO);
			member.setPoint(0L);
			member.setIsAuth(false);
			member.setParent(null);
			member.setGrade(1);
			member.setMemberRank(memberRankService.findDefault());
			return super.save(member);
		}
		return member;
	}

	@Override
	public Member findByUserTokenAndApp(String userToken, App app) {
		return find(JWTUtils.getKey(userToken,"openid"),app);
	}

	@Override
	public Map<String, Object> getData(Member member) {
		Map<String,Object> data = new HashMap<>();
		data.put("id",member.getId());
		data.put("balance",member.getBalance());
		data.put("amount",member.getAmount());
		data.put("avatarUrl",member.getAvatarUrl());
		data.put("rankName",member.getMemberRank().getName());
		data.put("nickName",member.getNickName());
		data.put("point",member.getPoint());
		data.put("days", DateUtils.getIntervalDay(new Date(),member.getCreatedDate()));
		data.put("isAuth",member.getIsAuth());
		// 观影时长
		data.putAll(jdbcTemplate.queryForMap("select SUM(seconds) visitSeconds from playrecord where member_id="+member.getId()));

		return data;
	}

	@Override
	public void addBalance(Member member, BigDecimal amount, MemberDepositLog.Type type, String memo) {
		Assert.notNull(member,"");
		Assert.notNull(amount,"");
		Assert.notNull(type,"");

		if (amount.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(memberDao.getLockMode(member))) {
			memberDao.flush();
			memberDao.refresh(member, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(member.getBalance(),"");
		Assert.state(member.getBalance().add(amount).compareTo(BigDecimal.ZERO) >= 0,"");

		member.setBalance(member.getBalance().add(amount));
		memberDao.flush();

		MemberDepositLog memberDepositLog = new MemberDepositLog();
		memberDepositLog.setType(type);
		memberDepositLog.setCredit(amount.compareTo(BigDecimal.ZERO) > 0 ? amount : BigDecimal.ZERO);
		memberDepositLog.setDebit(amount.compareTo(BigDecimal.ZERO) < 0 ? amount.abs() : BigDecimal.ZERO);
		memberDepositLog.setBalance(member.getBalance());
		memberDepositLog.setMemo(memo);
		memberDepositLog.setMember(member);
		memberDepositLogDao.persist(memberDepositLog);
	}

	@Override
	public void addBalance(Long memberId, BigDecimal amount, MemberDepositLog.Type type, String memo) {
		Member member = super.find(memberId);
		Assert.notNull(member,"");
		Assert.notNull(amount,"");
		Assert.notNull(type,"");

		if (amount.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(memberDao.getLockMode(member))) {
			memberDao.flush();
			memberDao.refresh(member, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(member.getBalance(),"");
		Assert.state(member.getBalance().add(amount).compareTo(BigDecimal.ZERO) >= 0,"");

		member.setBalance(member.getBalance().add(amount));
		memberDao.flush();

		MemberDepositLog memberDepositLog = new MemberDepositLog();
		memberDepositLog.setType(type);
		memberDepositLog.setCredit(amount.compareTo(BigDecimal.ZERO) > 0 ? amount : BigDecimal.ZERO);
		memberDepositLog.setDebit(amount.compareTo(BigDecimal.ZERO) < 0 ? amount.abs() : BigDecimal.ZERO);
		memberDepositLog.setBalance(member.getBalance());
		memberDepositLog.setMemo(memo);
		memberDepositLog.setMember(member);
		memberDepositLogDao.persist(memberDepositLog);
	}



	@Override
	public void addPoint(Member member, long amount, PointLog.Type type, String memo) {
		Assert.notNull(member,"");
		Assert.notNull(type,"");

		if (amount == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(memberDao.getLockMode(member))) {
			memberDao.flush();
			memberDao.refresh(member, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(member.getPoint(),"");
		Assert.state(member.getPoint() + amount >= 0,"");

		member.setPoint(member.getPoint() + amount);
		memberDao.flush();

		PointLog pointLog = new PointLog();
		pointLog.setType(type);
		pointLog.setCredit(amount > 0 ? amount : 0L);
		pointLog.setDebit(amount < 0 ? Math.abs(amount) : 0L);
		pointLog.setBalance(member.getPoint());
		pointLog.setMemo(memo);
		pointLog.setMember(member);
		pointLogDao.persist(pointLog);
	}

	@Override
	public void addAmount(Member member, BigDecimal amount) {
		Assert.notNull(member,"");
		Assert.notNull(amount,"");

		if (amount.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(memberDao.getLockMode(member))) {
			memberDao.flush();
			memberDao.refresh(member, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(member.getAmount(),"");
		Assert.state(member.getAmount().add(amount).compareTo(BigDecimal.ZERO) >= 0,"");

		member.setAmount(member.getAmount().add(amount));
		MemberRank memberRank = member.getMemberRank();
		if (memberRank != null && BooleanUtils.isFalse(memberRank.getIsSpecial())) {
			MemberRank newMemberRank = memberRankDao.findByAmount(member.getAmount());
			if (newMemberRank != null && newMemberRank.getAmount() != null && newMemberRank.getAmount().compareTo(memberRank.getAmount()) > 0) {
				member.setMemberRank(newMemberRank);
			}
		}
		memberDao.flush();
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