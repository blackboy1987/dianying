package com.bootx.app.dianying;

import com.bootx.entity.App;
import com.bootx.entity.SubscriptionRecord;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.service.AppService;
import com.bootx.service.SubscriptionRecordService;
import com.bootx.service.SubscriptionTemplateService;
import com.bootx.util.WechatUtils;
import com.bootx.util.wechat.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("initController")
@RequestMapping("/init")
public class InitController {

    @Autowired
    private AppService appService;

    @Resource
    private SubscriptionTemplateService subscriptionTemplateService;
    @Resource
    private SubscriptionRecordService subscriptionRecordService;

    @GetMapping
    public void init(Long id){
        App app = appService.find(id);
        if(app!=null){
            Map<String, App.AdConfig> ads = new HashMap<>();
            App.AdConfig indexAdConfig = ads.get("index");
            if(indexAdConfig==null){
                indexAdConfig = new App.AdConfig();

            }
            indexAdConfig.setBannerId("adunit-a677d814a70242ba");
            indexAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            indexAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            indexAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            indexAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            indexAdConfig.setVideoAdId("adunit-958e71b940f00867");
            indexAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("index",indexAdConfig);

            App.AdConfig detailAdConfig = ads.get("detail");
            if(detailAdConfig==null){
                detailAdConfig = new App.AdConfig();

            }
            detailAdConfig.setBannerId("adunit-a677d814a70242ba");
            detailAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            detailAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            detailAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            detailAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            detailAdConfig.setVideoAdId("adunit-958e71b940f00867");
            detailAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("detail",detailAdConfig);

            App.AdConfig feiLeiAdConfig = ads.get("feiLei");
            if(feiLeiAdConfig==null){
                feiLeiAdConfig = new App.AdConfig();
            }
            feiLeiAdConfig.setBannerId("adunit-a677d814a70242ba");
            feiLeiAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            feiLeiAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            feiLeiAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            feiLeiAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            feiLeiAdConfig.setVideoAdId("adunit-958e71b940f00867");
            feiLeiAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("feiLei",indexAdConfig);

            App.AdConfig topicAdConfig = ads.get("topic");
            if(topicAdConfig==null){
                topicAdConfig = new App.AdConfig();
            }
            topicAdConfig.setBannerId("adunit-a677d814a70242ba");
            topicAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            topicAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            topicAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            topicAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            topicAdConfig.setVideoAdId("adunit-958e71b940f00867");
            topicAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("topic",topicAdConfig);

            App.AdConfig playAdConfig = ads.get("play");
            if(playAdConfig==null){
                playAdConfig = new App.AdConfig();
            }
            playAdConfig.setBannerId("adunit-a677d814a70242ba");
            playAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            playAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            playAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            playAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            playAdConfig.setVideoAdId("adunit-958e71b940f00867");
            playAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("play",playAdConfig);

            App.AdConfig woDeAdConfig = ads.get("woDe");
            if(woDeAdConfig==null){
                woDeAdConfig = new App.AdConfig();
            }
            woDeAdConfig.setBannerId("adunit-a677d814a70242ba");
            woDeAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            woDeAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            woDeAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            woDeAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            woDeAdConfig.setVideoAdId("adunit-958e71b940f00867");
            woDeAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("woDe",woDeAdConfig);

            App.AdConfig otherAdConfig = ads.get("other");
            if(otherAdConfig==null){
                otherAdConfig = new App.AdConfig();
            }
            otherAdConfig.setBannerId("adunit-a677d814a70242ba");
            otherAdConfig.setGridAdId("adunit-215747ef06bf81a0");
            otherAdConfig.setInterstitialAdId("adunit-d73c982ac406d17a");
            otherAdConfig.setNativeAdId("adunit-0bf49dd0064f27b1");
            otherAdConfig.setRewardedVideoAdId("adunit-e9566ae9ffbf6ad8");
            otherAdConfig.setVideoAdId("adunit-958e71b940f00867");
            otherAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("other",otherAdConfig);
            app.setAds(ads);
            appService.update(app);
        }
    }

    @GetMapping("/sub")
    public void sub(Long id,String templateId){
        App app = appService.find(id);
        if(app!=null){
            boolean exist = subscriptionTemplateService.exists(app,templateId);
            if(!exist){
                SubscriptionTemplate subscriptionTemplate = new SubscriptionTemplate();
                subscriptionTemplate.setTemplateId(templateId);
                subscriptionTemplate.setApp(app);
                Map<String, SubscriptionTemplate.Value> params = new HashMap<>();
                params.put("thing1",new SubscriptionTemplate.Value());
                params.put("thing2",new SubscriptionTemplate.Value());
                subscriptionTemplate.setParam(params);
                subscriptionTemplateService.save(subscriptionTemplate);
            }
            appService.update(app);
        }
    }


    @GetMapping("/send")
    public void send(){
        List<SubscriptionRecord> subscriptionRecords = subscriptionRecordService.findList(2L);
        for (SubscriptionRecord subscriptionRecord:subscriptionRecords) {
            Map<String, SubscriptionTemplate.Value> param = subscriptionRecord.getSubscriptionTemplate().getParam();
            Map<String, Object> param1 = new HashMap<>();
            for (String key:param.keySet()) {
                if(StringUtils.equalsAnyIgnoreCase("thing1",key)){
                    param.put(key,new SubscriptionTemplate.Value("更新内容: 标题"));
                }else if(StringUtils.equalsAnyIgnoreCase("thing2",key)){
                    param.put(key,new SubscriptionTemplate.Value("更新栏目:标题"));
                }
            }
            param1.put("page","pages/detail/detail?id="+subscriptionRecord.getParam().get("vod_id"));
            param1.put("data",param);
            BaseResponse baseResponse = WechatUtils.subscribeSend(subscriptionRecord.getApp(),subscriptionRecord.getSubscriptionTemplate().getTemplateId(),subscriptionRecord.getMember().getOpenId(),param1);
            if(baseResponse.getErrCode()==0){
                // 发送成功
                subscriptionRecord.setStatus(1);
            }else{
                // 发送失败
                subscriptionRecord.setStatus(2);
            }
        }
    }

}
