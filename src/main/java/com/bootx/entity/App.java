package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class App extends BaseEntity<Long>{

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    public String appId;

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String appSecret;

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String appToken;

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String appCode;

    @NotNull
    @Column(nullable = false,unique = true)
    private String appName;

    /**
     * 0: 微信待审核
     * 1：微信审核通过
     * 2：已禁用
     */
    @NotNull
    @Column(nullable = false)
    private Integer status;

    private String logo;

    /**
     * 广告配置
     */
    @NotNull
    @Convert(converter = AdConfigConvert.class)
    @Column(length = 3000,nullable = false)
    private Map<String,AdConfig> ads = new HashMap<>();

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, AdConfig> getAds() {
        if(ads==null){
            ads = new HashMap<>();
        }
        return ads;
    }

    public void setAds(Map<String, AdConfig> ads) {
        this.ads = ads;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AdConfig implements Serializable {

        /**
         * Banner 广告
         */
        private String bannerId;

        /**
         * 激励视频广告
         */
        private String rewardedVideoAdId;

        /**
         * 插屏广告
         */
        private String interstitialAdId;

        /**
         * 视频广告
         */
        private String videoAdId;

        /**
         * 视频前贴广告(视频贴片广告)
         */
        private String videoFrontAdId;

        /**
         * 格子广告
         */
        private String gridAdId;

        /**
         * 原生模板广告
         */
        private String nativeAdId;

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }

        public String getRewardedVideoAdId() {
            return rewardedVideoAdId;
        }

        public void setRewardedVideoAdId(String rewardedVideoAdId) {
            this.rewardedVideoAdId = rewardedVideoAdId;
        }

        public String getInterstitialAdId() {
            return interstitialAdId;
        }

        public void setInterstitialAdId(String interstitialAdId) {
            this.interstitialAdId = interstitialAdId;
        }

        public String getVideoAdId() {
            return videoAdId;
        }

        public void setVideoAdId(String videoAdId) {
            this.videoAdId = videoAdId;
        }

        public String getVideoFrontAdId() {
            return videoFrontAdId;
        }

        public void setVideoFrontAdId(String videoFrontAdId) {
            this.videoFrontAdId = videoFrontAdId;
        }

        public String getGridAdId() {
            return gridAdId;
        }

        public void setGridAdId(String gridAdId) {
            this.gridAdId = gridAdId;
        }

        public String getNativeAdId() {
            return nativeAdId;
        }

        public void setNativeAdId(String nativeAdId) {
            this.nativeAdId = nativeAdId;
        }
    }

    @Convert
    public static class AdConfigConvert extends BaseAttributeConverter<Map<String,AdConfig>>{

    }
}
