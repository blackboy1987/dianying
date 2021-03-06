package com.bootx.app.chengyu;

import com.bootx.app.chengyu.service.IdiomLevelService;
import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.member.entity.Member;
import com.bootx.member.entity.MemberDepositLog;
import com.bootx.member.entity.PointLog;
import com.bootx.member.service.MemberService;
import com.bootx.member.service.PointLogService;
import com.bootx.service.AppService;
import org.jsoup.internal.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController("apiChengYuIndexController")
@RequestMapping("/api/chengyu")
public class IndexController {

    @Resource
    private MemberService memberService;
    @Resource
    private IdiomLevelService idiomLevelService;

    @Resource
    private PointLogService pointLogService;

    @Resource
    private AppService appService;

    /**
     * 根据关卡获取数据
     * @param level
     * @param request
     * @return
     */
    @PostMapping("/get")
    public Result get(Integer level,HttpServletRequest request){
        Member member = memberService.get(request);
        if(member==null){
            return Result.error("请先登录");
        }
        if(level==null){
            level = member.getLevel();
        }
        return Result.success(idiomLevelService.get(level+1));
    }

    /**
     * 排行榜
     */
    @PostMapping("/rank")
    public Result rank(HttpServletRequest request){
        App app = appService.get(request);
        if(app==null){
            return Result.error("非法访问");
        }
        Map<String,Object> data = new HashMap<>();
        data.put("list",memberService.rank(app,30));
        data.put("type",1);
        return Result.success(data);
    }



    /**
     * 答题扣减积分
     */
    @PostMapping("/discount")
    public Result discount(HttpServletRequest request,String memo,Integer level,Integer type){
        Member member = memberService.get(request);
        App app = appService.get(request);
        if(!member.getAppId().equals(app.getId())){
            return Result.error("非法访问");
        }
        String levelPoint = app.getConfig().get("levelPoint");
        if(StringUtil.isBlank(levelPoint)){
            levelPoint = "100";
        }
        if(type!=null&&type==1){
            member.setLevel(level);
        }
        memberService.addPoint(member,Long.parseLong(levelPoint)*(-1), PointLog.Type.deduct,level+":"+memo);
        return Result.success("ok");
    }

    /**
     * 红包开启
     */
    @PostMapping("/redPackage")
    public Result redPackage(HttpServletRequest request, Integer level){
        Member member = memberService.get(request);
        App app = appService.get(request);
        if(!member.getAppId().equals(app.getId())){
            return Result.error("非法访问");
        }
        Map<Integer,RedPackageConfig> map = parseRedPackageConfig(app);
        RedPackageConfig redPackageConfig = map.get(level);
        if(redPackageConfig!=null){
            BigDecimal amount = (redPackageConfig.getMax().subtract(redPackageConfig.getMax())).multiply(new BigDecimal(new Random().nextFloat())).add(redPackageConfig.getMin());
            memberService.addBalance(member,amount, MemberDepositLog.Type.reward,level+":"+"红包开启");
            return Result.success(amount);
        }
        return Result.error("今日红包已瓜分完毕");

    }

    private Map<Integer,RedPackageConfig> parseRedPackageConfig(App app){
        Map<Integer,RedPackageConfig> map = new HashMap<>();
        String redPackage = app.getConfig().get("redPackage");
        if(!StringUtil.isBlank(redPackage)){
            //  解析红包配置数据
            String[] redPackageConfigStrs = redPackage.split(";");
            for (String redPackageConfigStr:redPackageConfigStrs){
                String[] configs = redPackageConfigStr.split("_");
                RedPackageConfig redPackageConfig = new RedPackageConfig();
                redPackageConfig.setLevel(Integer.valueOf(configs[0]));
                if(configs.length==2){
                    redPackageConfig.setMin(new BigDecimal(configs[1]));
                    redPackageConfig.setMax(new BigDecimal(configs[1]));
                }else if(configs.length==3){
                    redPackageConfig.setMin(new BigDecimal(configs[1]));
                    redPackageConfig.setMax(new BigDecimal(configs[2]));
                }
                map.put(redPackageConfig.getLevel(), redPackageConfig);
            }
        }
        return map;
    }


    public static class RedPackageConfig {

        private Integer level;

        private BigDecimal min;

        private BigDecimal max;

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public BigDecimal getMin() {
            return min;
        }

        public void setMin(BigDecimal min) {
            this.min = min;
        }

        public BigDecimal getMax() {
            return max;
        }

        public void setMax(BigDecimal max) {
            this.max = max;
        }
    }
}
