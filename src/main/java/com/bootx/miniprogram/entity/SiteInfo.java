package com.bootx.miniprogram.entity;

import com.bootx.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity

@Table(name = "zhaocha_site_info")
public class SiteInfo extends BaseEntity<Long> {

    @OneToOne
    @NotNull
    @JoinColumn(updatable = false,nullable = false)
    private App app;

    /**
     * 一次可以玩多少局
     */
    @JsonView({ViewView.class})
    private Integer ticketMax;

    /**
     * ticketMax 减为0，之后需要休息的时间。单位分钟
     */
    @JsonView({ViewView.class})
    private Integer ticketTime;

    /**
     * 是否可以分享
     */
    @JsonView({ViewView.class})
    private Boolean isShare;

    /**
     * 分享的图片地址
     */
    @JsonView({ViewView.class})
    private String shareImg;

    /**
     * 分享的标题
     */
    private String shareTitle;

    /**
     * 每局游戏进行时间
     */
    @JsonView({ViewView.class})
    private Integer gameTime;

    /**
     * 点击错误点，扣除的积分数
     */
    @JsonView({ViewView.class})
    private Integer downTime;

    /**
     *
     */
    @JsonView({ViewView.class})
    private String serviceImg;

    @JsonView({ViewView.class})
    private String serviceTitle;

    @JsonView({ViewView.class})
    private Integer useGold;

    /**
     * 是否显示广告
     */
    @JsonView({ViewView.class})
    private Boolean adshow;


    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Integer getTicketMax() {
        return ticketMax;
    }

    public void setTicketMax(Integer ticketMax) {
        this.ticketMax = ticketMax;
    }

    public Integer getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Integer ticketTime) {
        this.ticketTime = ticketTime;
    }

    public Boolean getShare() {
        return isShare;
    }

    public void setShare(Boolean share) {
        isShare = share;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    public Integer getDownTime() {
        return downTime;
    }

    public void setDownTime(Integer downTime) {
        this.downTime = downTime;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public Integer getUseGold() {
        return useGold;
    }

    public void setUseGold(Integer useGold) {
        this.useGold = useGold;
    }

    public Boolean getAdshow() {
        return adshow;
    }

    public void setAdshow(Boolean adshow) {
        this.adshow = adshow;
    }
}
