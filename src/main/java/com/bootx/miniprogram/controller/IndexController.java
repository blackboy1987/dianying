package com.bootx.miniprogram.controller;

import com.bootx.common.Result;
import com.bootx.entity.Level;
import com.bootx.entity.LevelImage;
import com.bootx.miniprogram.entity.App;
import com.bootx.miniprogram.entity.Member;
import com.bootx.miniprogram.service.AppService;
import com.bootx.miniprogram.service.MemberService;
import com.bootx.service.LevelService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("miniprogramIndexController")
@RequestMapping("/minprogram")
public class IndexController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AppService appService;

    @Autowired
    private LevelService levelService;

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
        if(StringUtils.isNotEmpty(openId)){
            Member member = memberService.create(openId,app);
            data.put("id",member.getId());
            data.put("token",member.getId());
        }
        return Result.success(data);
    }

    @GetMapping("/level")
    public Result level(Long id){
        Level level = levelService.find(id);
        Map<String,Object> data = new HashMap<>();
        if(level!=null){
            List<LevelImage> content = level.getContent();
            for (LevelImage levelImage:content) {
                if(StringUtils.equals("layer",levelImage.getName())){
                    levelImage.setUrl("https://bootx-zhaocha.oss-cn-hangzhou.aliyuncs.com/images/lv/"+level.getId()+"/"+levelImage.getName()+".jpg");
                }else{
                    levelImage.setUrl("https://bootx-zhaocha.oss-cn-hangzhou.aliyuncs.com/images/lv/"+level.getId()+"/"+levelImage.getName()+".png");
                }
            }
            levelService.update(level);
        }
        data.put("value",level.getId());
        data.put("question",level.getContent());
        return Result.success(data);
    }
}
