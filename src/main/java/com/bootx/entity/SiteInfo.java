package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SiteInfo extends BaseEntity<Long> {

    @NotNull
    @Column(updatable = false,unique = true,nullable = false)
    private Long appId;

    private String name;

    private String logo;

    /**
     *Banner 广告
     */
    private String bannerAdId;

    /**
     *激励视频广告
     */
    private String rewardedVideoAdId;

    /**
     * 插屏广告
     */
    private String interstitialAdId;

    /**
     *小程序视频广告
     */
    private String videoAdId;

    /**
     *视频前贴广告
     */
    private String videoFrontAdId;

    /**
     *Grid 广告
     */
    private String gridAdId;

    /**
     *原生模板 广告
     */
    private String nativeAdId;

    /**
     * 状态：
     * 0：关闭
     * 1：审核中
     * 2：审核通过
     */
    private Integer status;

    private Boolean openPoint;

    /**
     * 至少观看多少分钟，才获取积分奖励
     */
    private Long minVisitMinute;

    /**
     * 每分钟奖励多少积分
     */
    private Long everyMinuteToPoint;

    /**
     * 跳过广告，扣除积分
     */
    private Long jumpAdDiscoutPoint;

    private Boolean openAd;

    @Lob
    @Convert(converter = CarouselConverter.class)
    @JsonView({BaseEntity.ViewView.class})
    private List<Carousel> carousels = new ArrayList<>();


    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBannerAdId() {
        return bannerAdId;
    }

    public void setBannerAdId(String bannerAdId) {
        this.bannerAdId = bannerAdId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Carousel> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<Carousel> carousels) {
        this.carousels = carousels;
    }

    public Boolean getOpenPoint() {
        return openPoint;
    }

    public void setOpenPoint(Boolean openPoint) {
        this.openPoint = openPoint;
    }

    public Long getMinVisitMinute() {
        return minVisitMinute;
    }

    public void setMinVisitMinute(Long minVisitMinute) {
        this.minVisitMinute = minVisitMinute;
    }

    public Long getEveryMinuteToPoint() {
        return everyMinuteToPoint;
    }

    public void setEveryMinuteToPoint(Long everyMinuteToPoint) {
        this.everyMinuteToPoint = everyMinuteToPoint;
    }

    public Long getJumpAdDiscoutPoint() {
        return jumpAdDiscoutPoint;
    }

    public void setJumpAdDiscoutPoint(Long jumpAdDiscoutPoint) {
        this.jumpAdDiscoutPoint = jumpAdDiscoutPoint;
    }

    public Boolean getOpenAd() {
        return openAd;
    }

    public void setOpenAd(Boolean openAd) {
        this.openAd = openAd;
    }

    /**
     * 类型转换 - 可选项
     *
     * @author bootx Team
     * @version 6.1
     */
    @Converter
    public static class CarouselConverter extends BaseAttributeConverter<List<Carousel>> {
    }


    public static class Carousel implements Serializable{

        private Long id;

        private String url;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
