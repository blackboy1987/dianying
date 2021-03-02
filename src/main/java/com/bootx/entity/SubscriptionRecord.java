package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.bootx.member.entity.Member;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 订阅记录
 */
@Entity
public class SubscriptionRecord extends BaseEntity<Long>{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private App app;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private SubscriptionTemplate subscriptionTemplate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private Member member;

    /**
     * 0: 已订阅
     * 1：已发送消息
     * 2：发送失败
     * 3：已过期
     * 4：已取消
     */
    @NotNull
    @Column(nullable = false)
    private Integer status;

    /**
     * 发送的内容 json格式的字符串
     */
    private String content;

    @Convert(converter = ParamConverter.class)
    private Map<String, String> param = new HashMap<>();

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public SubscriptionTemplate getSubscriptionTemplate() {
        return subscriptionTemplate;
    }

    public void setSubscriptionTemplate(SubscriptionTemplate subscriptionTemplate) {
        this.subscriptionTemplate = subscriptionTemplate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    @Converter
    public static class ParamConverter extends BaseAttributeConverter<Map<String, String>> {

    }
}
