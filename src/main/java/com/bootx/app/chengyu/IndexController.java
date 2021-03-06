package com.bootx.app.chengyu;

import com.bootx.app.chengyu.service.IdiomLevelService;
import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.member.entity.Member;
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
import java.util.HashMap;
import java.util.Map;

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
    public Result discount(HttpServletRequest request,String memo,Integer level){
        Member member = memberService.get(request);
        App app = appService.get(request);
        if(member.getAppId()!=app.getId()){
            return Result.error("非法访问");
        }
        String levelPoint = app.getConfig().get("levelPoint");
        if(StringUtil.isBlank(levelPoint)){
            levelPoint = "100";
        }
        memberService.addPoint(member,Long.parseLong(levelPoint)*(-1), PointLog.Type.deduct,level+":"+memo);
        return Result.success("ok");
    }

    /**
     * 红包开启
     */

    /**
     * 看广告得积分
     */

    /**
     * 看广告得红包
     */
}
