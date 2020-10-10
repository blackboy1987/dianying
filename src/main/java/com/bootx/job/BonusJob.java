package com.bootx.job;

import com.bootx.entity.Member;
import com.bootx.entity.MemberDepositLog;
import com.bootx.service.MemberService;
import com.bootx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class BonusJob {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberService memberService;

    /**
     * 每天12点10分，进行统计
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void run(){
        Map<Long, BigDecimal> members = new HashMap<>();
        Date beginDate = DateUtils.getNextDay(-1);
        Date endDate = DateUtils.getEndDay(beginDate);
        beginDate = DateUtils.getBeginDay(beginDate);
        String sql = "select sum(seconds) seconds,member_id memberId from playrecord WHERE createdDate>='"+DateUtils.formatDateToString(beginDate,"yyyy-MM-dd HH:mm:ss")+"' and createdDate<'"+DateUtils.formatDateToString(endDate,"yyyy-MM-dd HH:mm:ss")+"' group by member_id ";
        System.out.println(sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        maps.forEach(item->{
            Long seconds = Long.valueOf(item.get("seconds").toString());
            Long memberId = Long.valueOf(item.get("memberId").toString());
            try {
                Member member = memberService.find(memberId);
                Member parent = member.getParent();
                BigDecimal balance = new BigDecimal(seconds);
                // 10分钟1分钱
                balance = balance.multiply(new BigDecimal(0.01)).divide(new BigDecimal(60)).divide(new BigDecimal(10));
                if(members.containsKey(parent.getId())){
                    members.put(parent.getId(),members.get(parent).add(balance));
                }else{
                    members.put(parent.getId(),balance);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        for (Long id:members.keySet()) {
            memberService.addBalance(id,members.get(id), MemberDepositLog.Type.reward,"看视频奖励");
        }

    }
}
