package com.bootx.controller;

import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.member.entity.Member;
import com.bootx.member.service.MemberService;
import com.bootx.service.AppService;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController("apiIndexController")
@RequestMapping("/api")
public class IndexController {

    @Resource
    private AppService appService;

    @Resource
    private MemberService memberService;

    /**
     * 登录(进入小程序就会调用该方法)
     * @param code
     * @param scene
     *      其实就是推荐人的id
     * @return
     */
    @PostMapping("/login")
    private Result login(String code, HttpServletRequest request,Long scene) {
        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        App app = appService.get(request);
        if(app==null){
            return Result.error("非法请求");
        }
        Map<String,String> params = new HashMap<>();
        params.put("appid",app.getAppId());
        params.put("secret",app.getAppSecret());
        params.put("js_code",code);
        params.put("grant_type","authorization_code");
        Map<String,String> result = JsonUtils.toObject(WebUtils.get1(url, params), new TypeReference<Map<String, String>>() {});

        Member member = memberService.create(result,app,scene);
        Map<String,Object> data1 = memberService.getData(member);
        data.put("userInfo",data1);
        data.put("code",200);
        data.put("token", JWTUtils.create(member.getId()+"",data1));
        return Result.success(data);
    }


    /**
     * 站点信息
     * @param request
     * @return
     */
    @PostMapping("/site")
    private Result login(HttpServletRequest request) throws IOException {
        App app = appService.get(request);
        if(app==null){
            return Result.error("非法请求");
        }

        //WechatUtils.createQRCode(app, QRCodeParam.create("123"));


        Map<String,Object> data = new HashMap<>();
        data.put("name",app.getAppName());
        data.put("ads",app.getAds());
        return Result.success(data);
    }

    /**
     * 更新用户信息
     * @param nickName
     * @param gender
     * @param city
     * @param province
     * @param country
     * @param avatarUrl
     * @param request
     * @return
     */
    @PostMapping("/updateUserInfo")
    private Result updateUserInfo(String nickName,Integer gender,String city,String province,String country,String avatarUrl,HttpServletRequest request) {

        Member member = memberService.get(request);
        if(member!=null){
            member.setNickName(nickName);
            member.setGender(gender);
            member.setCity(city);
            member.setProvince(province);
            member.setCountry(country);
            member.setAvatarUrl(avatarUrl);
            memberService.update(member);
        }
        return Result.success("");
    }

}
