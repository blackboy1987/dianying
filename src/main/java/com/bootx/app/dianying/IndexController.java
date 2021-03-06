package com.bootx.app.dianying;

import com.bootx.app.dianying.pojo.Demo;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.member.entity.Member;
import com.bootx.member.service.MemberService;
import com.bootx.service.AppService;
import com.bootx.service.SubscriptionRecordService;
import com.bootx.service.SubscriptionTemplateService;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("apiV3IndexController")
@RequestMapping("/api/v3")
public class IndexController {

    @Autowired
    private AppService appService;

    @Autowired
    private MemberService memberService;

    @Resource
    private SubscriptionTemplateService subscriptionTemplateService;

    @Resource
    private SubscriptionRecordService subscriptionRecordService;

    private static final String[] otherService = {
            "App.Art.GetArtId",
            "App.Mov.HotKeywords","App.Mov.SearchVod","App.Mov.GetLevelId","App.Mov.GetConfig",
            "App.Mov.GetOnlineMvById","App.Mov.SetMovById","App.Mov.GetCategory","App.Mov.GetSyLevelAll",
            "App.Mov.GetHomeLevelAll","App.Mov.HotKeywords","App.Mov.SearchVod",
            "App.Topic.GetTopicAll","App.Topic.GetTopicId",
            "App.User.WxRegister","App.User.PidNum","App.User.WxLogin",
            "App.Ulog.SetSubscribe","App.Ulog.GetLog",
            "App.Code.WxPoster",
            "App.JX.VipJX"
    };

    /**
     * 搜索
     * @param request
     * @param wd
     * @return
     */
    @GetMapping("/api.php/provide/search")
    public String search(HttpServletRequest request,String wd){
        String service = request.getParameter("service");
        App app = appService.get(request);
        if(app==null){
            return JsonUtils.toJson(Result.error(406,"非法访问"));
        }
        Map<String,String> params = new HashMap<>();
        params.put("wd",wd);

        String result = WebUtils.get1("https://oss.1259416862.shanghai.mysql.ttqbaby.com/api.php/provide/search/",params);
        return parseResult(result,service,app);
    }


    @GetMapping("/wxApi/public")
    public String categories(HttpServletRequest request){
        String service = request.getParameter("service");
        App app = appService.get(request);
        if(app==null){
            return JsonUtils.toJson(Result.error(406,"非法访问"));
        }
        Map<String,String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            if(!StringUtils.equalsAnyIgnoreCase("token",key)&&!StringUtils.equalsAnyIgnoreCase("appCode",key)&&!StringUtils.equalsAnyIgnoreCase("appToken",key)&&!StringUtils.equalsAnyIgnoreCase("tmpIds",key)){
                params.put(key,request.getParameter(key));
            }
        }

        // 一键登录，自行处理
        if(StringUtils.equalsAnyIgnoreCase("App.User.WxRegister",service)){
            return login(params,app);
        }
        // 订阅
        if(StringUtils.equalsAnyIgnoreCase("App.Ulog.SetSubscribe",service)){
            return setSubscribes(request,app);
        }

        String result = WebUtils.get1("https://dl.yulu123.xyz/wxApi/public/",params);
        return parseResult(result,service,app);
    }


    /**
     * 订阅
     * vod_id: 214164
     * type_id: 1
     * ulog_nid: 1
     * user_id: 3
     * mark: suisui
     * openid: undefined
     * sign: d7c458832c4ed7f51aac87090d2b2ebb
     * token: eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzIiwic3ViIjoi5bCP55m9IiwiaWF0IjoxNjE0Njc0Njk5LCJleHAiOjE2MTYxNDU5MjgsImFtb3VudCI6MEUtMTIsImlzQXV0aCI6ZmFsc2UsImJhbGFuY2UiOjBFLTEyLCJhdmF0YXJVcmwiOiJodHRwczovL3RoaXJkd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL1EwajRUd0dUZlRKaWJWZXViNUhubFMzT1JrOFM0cVZ1QmFpY2YxU09IZk9URlJOVVVmOWNrN0lMUldyTHVYTG1kQkxKMk5nZVlpYUlhYnRLaWFPOUszaWJuancvMTMyIiwidXNlcl9pZCI6Mywibmlja05hbWUiOiLkuIDmnprluIXlk6UiLCJyYW5rTmFtZSI6IuaZrumAmuS8muWRmCIsImlkIjozLCJwb2ludCI6MH0.Zl5cA4FziIalOHSUvNAF3Ag6QrnMcv9UEXOPMkvHx3c
     * appCode: IEC4OARSJZAB4SG3TA
     * appToken: fcb1123588b8c311c661e2e2f6bff63195fb1932809403507e67044dfadgg755
     * @param request
     * @param app
     */
    private String setSubscribes(HttpServletRequest request, App app) {
        // 视频id
        String vod_id = request.getParameter("vod_id");
        // 当前账号
        Member member = memberService.get(request);
        // 订阅的条数
        String ulog_nid = request.getParameter("ulog_nid");
        // 视频所属分类
        String type_id = request.getParameter("type_id");
        /**
         * 订阅的模板id,多个用逗号隔开
         */
        String tmpIds = request.getParameter("tmpIds");
        Long count = 0L;
        if(StringUtils.isNotBlank(tmpIds)&&app!=null&&member!=null&&member.getAppId()==app.getId()){
            List<SubscriptionTemplate> subscriptionTemplates =  subscriptionTemplateService.findListByAppAndSubscriptionTemplateIds(app,tmpIds.split(","));
            for (SubscriptionTemplate subscriptionTemplate:subscriptionTemplates) {
                // 判断当天该模板该用户订阅的次数
                count = subscriptionRecordService.count(app,member,subscriptionTemplate);
                if(count>=3){
                    continue;
                }
                SubscriptionRecord subscriptionRecord = new SubscriptionRecord();
                subscriptionRecord.setApp(app);
                subscriptionRecord.setStatus(0);
                subscriptionRecord.setMember(member);
                subscriptionRecord.setSubscriptionTemplate(subscriptionTemplate);
                subscriptionRecord.getParam().put("vod_id",vod_id);
                subscriptionRecord.getParam().put("type_id",type_id);
                subscriptionRecordService.save(subscriptionRecord);
                count = count+1;
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","订阅成功");
        map.put("send_num",count);
        return JsonUtils.toJson(Result.success(map));
    }


    /**
     * 登录
     * @param params1
     * @param app
     * @return
     */
    private String login(Map<String,String> params1,App app) {
        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> params = new HashMap<>();
        params.put("appid",app.getAppId());
        params.put("secret",app.getAppSecret());
        params.put("js_code",params1.get("user"));
        params.put("grant_type","authorization_code");
        Map<String,String> result = JsonUtils.toObject(WebUtils.get1(url, params), new TypeReference<Map<String, String>>() {});
        result.putAll(params1);

        Member member = memberService.create(result,app,null);
        Map<String,Object> data1 = new HashMap<>(result);
        data1 = memberService.getData(member);
        data.put("userInfo",data1);
        data.put("code",200);
        data.put("token", JWTUtils.create(member.getId()+"",data1));
        return JsonUtils.toJson(Result.success(data));
    }

    private String parseResult(String result,String service,App app) {
        if(StringUtils.equalsAnyIgnoreCase("App.Mov.GetConfig",service)){
            return getConfig(result,app);
        }

        // App.User.WxRegister&user

        return result;
    }

    private String getConfig(String result,App app) {
        Demo demo = JsonUtils.toObject(result,Demo.class);
        demo.getData().setAdmin(app.getAppName());
        Map<String, App.AdConfig> ads = app.getAds();
        App.AdConfig indexAdConfig = ads.get("index");
        if(indexAdConfig!=null){
            demo.getData().getIndex().getWxAdId().setYsId(indexAdConfig.getNativeAdId());
            demo.getData().getIndex().getWxAdId().setCpId(indexAdConfig.getInterstitialAdId());
        }

        App.AdConfig detailAdConfig = ads.get("detail");
        if(detailAdConfig!=null){
            demo.getData().getDetail().getWxAdId().setYsId(detailAdConfig.getNativeAdId());
            demo.getData().getDetail().getWxAdId().setCpId(detailAdConfig.getInterstitialAdId());
            demo.getData().getDetail().getWxAdId().setJlspId(detailAdConfig.getRewardedVideoAdId());
        }
        App.AdConfig fenLeiAdConfig = ads.get("fenLei");
        if(fenLeiAdConfig!=null){
            demo.getData().getFeilei().getWxAdId().setYsId(fenLeiAdConfig.getNativeAdId());
            demo.getData().getFeilei().getWxAdId().setCpId(fenLeiAdConfig.getInterstitialAdId());
        }
        // demo.getData().getFeilei().getWxAdId().setJlspId("adunit-d06d530e38aaba45");
        App.AdConfig topicAdConfig = ads.get("topic");
        if(topicAdConfig!=null){
            demo.getData().getTopic().getWxAdId().setYsId(topicAdConfig.getNativeAdId());
            demo.getData().getTopic().getWxAdId().setCpId(topicAdConfig.getInterstitialAdId());
        }
        //  demo.getData().getTopic().getWxAdId().setJlspId("adunit-d06d530e38aaba45");
        App.AdConfig playAdConfig = ads.get("play");
        if(playAdConfig!=null){
            demo.getData().getPlay().getWxAdId().setYsId(playAdConfig.getNativeAdId());
            demo.getData().getPlay().getWxAdId().setCpId(playAdConfig.getInterstitialAdId());
            demo.getData().getPlay().getWxAdId().setJlspId(playAdConfig.getRewardedVideoAdId());
            demo.getData().getPlay().getWxAdId().setSpqtId(playAdConfig.getVideoFrontAdId());
        }
        App.AdConfig woDeAdConfig = ads.get("woDe");
        if(woDeAdConfig!=null){
            demo.getData().getWode().getWxAdId().setYsId(woDeAdConfig.getNativeAdId());
        }
        // demo.getData().getWode().getWxAdId().setCpId("adunit-d73c982ac406d17a");
        // demo.getData().getWode().getWxAdId().setJlspId("adunit-d06d530e38aaba45");
        //demo.getData().getWode().getWxAdId().setSpqtId("adunit-03083c87b390182a");
        App.AdConfig otherAdConfig = ads.get("other");
        if(otherAdConfig!=null){
            demo.getData().getOther().getWxAdId().setYsId(otherAdConfig.getNativeAdId());
            demo.getData().getOther().getWxAdId().setCpId(otherAdConfig.getInterstitialAdId());
        }
        //demo.getData().getOther().getWxAdId().setJlspId("adunit-d06d530e38aaba45");
        // demo.getData().getOther().getWxAdId().setSpqtId("adunit-03083c87b390182a");
        demo.getData().getSite().getKefu().setUrl(app.getLogo());
        demo.getData().setWxverify(app.getStatus()==2);
        demo.getData().getSite().getMessage().setTmpIds("AIUmDyqInlZT2qtC2hqFAzTB6MJ-YTeKSlNcF02OVP0");
        return JsonUtils.toJson(demo);
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result implements Serializable {

        @JsonProperty("Code")
        private Integer code;
        @JsonProperty("Data")
        private Object data;
        @JsonProperty("msg")
        private String msg;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * 构造方法
         */
        public Result() {
        }

        public Result(Integer code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public static Result success(Object data) {
            return new Result(200, "", data);
        }

        public static Result error(Integer code, String msg) {
            return new Result(code, msg, null);
        }
    }


}
