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

    public List<Carousel> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<Carousel> carousels) {
        this.carousels = carousels;
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
