package com.bootx.miniprogram.entity;

import com.bootx.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

@Table(name = "zhaocha_member")
public class Member extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String openId;

    @ManyToOne
    @NotNull
    @JoinColumn(updatable = false,nullable = false)
    private App app;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private MemberRank memberRank;















    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public MemberRank getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(MemberRank memberRank) {
        this.memberRank = memberRank;
    }
}
