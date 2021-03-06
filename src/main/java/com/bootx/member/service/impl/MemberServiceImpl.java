
package com.bootx.member.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.App;
import com.bootx.entity.RewardType;
import com.bootx.member.dao.MemberDao;
import com.bootx.member.dao.MemberDepositLogDao;
import com.bootx.member.dao.MemberRankDao;
import com.bootx.member.dao.PointLogDao;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.MemberDepositLog;
import com.bootx.member.entity.MemberRank;
import com.bootx.member.entity.PointLog;
import com.bootx.member.service.MemberRankService;
import com.bootx.member.service.MemberService;
import com.bootx.service.AppService;
import com.bootx.service.impl.BaseServiceImpl;
import com.bootx.util.DateUtils;
import com.bootx.util.JWTUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	@Resource
	private AppService appService;

	@Override
	public Member find(String openId, App app) {
		return memberDao.find(openId,app);
	}

	@Override
	public Member create(Map<String,String> map, App app,Long scene) {
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
			Member parent = findByAppAndId(app,scene);
			member = new Member();
			member.setLevel(0);
			member.setOpenId(openId);
			member.setUnionid(unionid);
			member.setSessionKey(sessionKey);
			member.setAppId(app.getId());
			member.setAmount(BigDecimal.ZERO);
			member.setBalance(BigDecimal.ZERO);
			member.setPoint(0L);
			member.setIsAuth(false);
			member.setParent(null);
			member.setGrade(1);
			member.setParent(parent);
			member.setMemberRank(memberRankService.findDefault(app));

			try {
				if(app.getConfig().get("registerRewardPoint")!=null){
					Long point = Long.valueOf(app.getConfig().get("registerRewardPoint"));
					member.setPoint(point);
				}
			}catch (Exception ignored){

			}
			try {
				if(app.getConfig().get("registerRewardBalance")!=null){
					member.setBalance(new BigDecimal(app.getConfig().get("registerRewardBalance")));
				}
			}catch (Exception ignored){

			}


			member = super.save(member);
		}
		member.setNickName(map.get("name"));
		member.setAvatarUrl(map.get("avatarUrl"));
		return super.update(member);
	}

	private Member findByAppAndId(App app, Long id) {
		Member member = find(id);
		if(member!=null&&member.getAppId()==app.getId()){
			return member;
		}
		return null;

	}

	@Override
	public Member findByUserTokenAndApp(String userToken, App app) {
		return find(JWTUtils.getKey(userToken,"openid"),app);
	}

	@Override
	public Map<String, Object> getData(Member member) {
		Map<String,Object> data = new HashMap<>();
		if(member==null){
			return data;
		}
		data.put("id",member.getId());
		data.put("balance",member.getBalance());
		data.put("amount",member.getAmount());
		data.put("point",member.getPoint());
		data.put("avatarUrl",member.getAvatarUrl());
		data.put("rankName",member.getMemberRank().getName());
		data.put("nickName",member.getNickName());
		data.put("isAuth",member.getIsAuth());
		data.put("user_id",member.getId());
		data.put("level",member.getLevel());
		data.put("reviewRewardedVideoAdCount", countPointLog(new Date(), member, PointLog.Type.reviewRewardedVideoAd));
		data.put("isSign", countPointLog(new Date(), member, PointLog.Type.sign)>0);

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
			MemberRank newMemberRank = memberRankDao.findByAmount(member.getAmount(),appService.find(member.getAppId()));
			if (newMemberRank != null && newMemberRank.getAmount() != null && newMemberRank.getAmount().compareTo(memberRank.getAmount()) > 0) {
				member.setMemberRank(newMemberRank);
			}
		}
		memberDao.flush();
	}

    @Override
    public Member get(HttpServletRequest request) {

		String token = request.getHeader("token");
		if(StringUtils.isBlank(token)){
			token = request.getParameter("token");
		}
		try {
			Claims claims = JWTUtils.parseToken(token);
			String id = claims.getId();
			return super.find(Long.valueOf(id));
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Map<String, Object>> rank(App app, int count) {
		return jdbcTemplate.queryForList("select id,level,nickName,avatarUrl from member where and appId="+app.getId()+" order by level desc limit "+count);
	}

	@Override
	public Integer countPointLog(Date date, Member member, PointLog.Type type) {
		if(date==null||member==null||type==null){
			return 0;
		}
		Integer count = jdbcTemplate.queryForObject("select count(id) from pointlog where type="+type.ordinal()+" and member_id="+member.getId()+" and DATE_FORMAT(createdDate,'%Y-%m-%d')=DATE_FORMAT('"+ DateUtils.formatDateToString(date, "yyyy-MM-dd") +"','%Y-%m-%d');", Integer.class);
		return count;
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