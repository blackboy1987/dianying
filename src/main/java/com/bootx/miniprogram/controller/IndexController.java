package com.bootx.miniprogram.controller;

import com.bootx.common.Result;
import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;
import com.bootx.miniprogram.service.AppService;
import com.bootx.miniprogram.service.MemberService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("miniprogramIndexController")
@RequestMapping("/minprogram")
public class IndexController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AppService appService;

    @GetMapping("/login")
    public Result index(String code, String appCode, String appSecret){

        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        App app = appService.findByCodeAndSecret(appCode,appSecret);
        Map<String,Object> params = new HashMap<>();
        params.put("appid",app.getAppId());
        params.put("secret",app.getAppSecret());
        params.put("js_code",code);
        params.put("grant_type","authorization_code");
        Map<String,String> result = JsonUtils.toObject(WebUtils.get(url, params), new TypeReference<Map<String, String>>() {});
        String openId = result.get("openid");
        Member member = memberService.create(openId,app);
        data.put("id",member.getId());
        data.put("token",member.getId());
        return Result.success(data);
    }
}
