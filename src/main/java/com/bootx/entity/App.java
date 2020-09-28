package com.bootx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
    private String token;

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String appCode;


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
