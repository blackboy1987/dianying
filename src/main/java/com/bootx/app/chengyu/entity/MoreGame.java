package com.bootx.app.chengyu.entity;

import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "chengyu_more_game")
public class MoreGame extends BaseEntity<Long> {

    @NotEmpty
    @Column(nullable = false)
    private String appId;

    @NotEmpty
    @Column(nullable = false)
    private String path;

    @NotEmpty
    @Column(nullable = false)
    private String memo;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String logo;
    @ManyToOne(fetch = FetchType.LAZY)
    private App app;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
