package com.bootx.chengyu;

import com.bootx.chengyu.entity.Idiom;
import com.bootx.chengyu.service.IdiomService;
import com.bootx.common.Result;
import com.bootx.member.entity.Member;
import com.bootx.member.service.MemberService;
import com.bootx.service.AppService;
import com.bootx.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiChengYuIndexController")
@RequestMapping("/api/chengyu")
public class IndexController {

    @Resource
    private AppService appService;

    @Resource
    private MemberService memberService;

    @Resource
    private IdiomService idiomService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/get")
    public Result get(Integer level,HttpServletRequest request){
        Member member = memberService.get(request);
        if(member==null){
            return Result.error("请先登录");
        }
        if(level==null){
            level = member.getLevel();
        }
        String s = stringRedisTemplate.opsForValue().get("idiom_" + (level + 1));
        if(StringUtils.isNotBlank(s)){
            return Result.success(s);
        }


        Idiom idiom = idiomService.findByLevel(level+1);
        if(idiom==null) {
            return Result.error("无数据");
        }

        Map<String,Object> data = new HashMap<>();
        data.put("level",idiom.getLevel());
        List<String> text = idiom.getText();
        data.put("text",text);
        data.put("answer",text.get(idiom.getPosition()));
        data.put("answers",idiom.getAnswers());
        data.put("level",idiom.getLevel());
        stringRedisTemplate.opsForValue().set("idiom_"+idiom.getLevel(), JsonUtils.toJson(data));
        return Result.success(data);
    }

    @GetMapping("/answers")
    public Result answers(){
        List<Idiom> idioms = new ArrayList<>();
        for (Idiom idiom:idioms) {
            idiom.setAnswers(setAnswers(idiom.getText().get(idiom.getPosition())));
        }




        return Result.success("");
    }

    private List<String> setAnswers(String s) {



    }
}
