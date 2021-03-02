package com.bootx.entity;


import com.bootx.common.BaseAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息订阅模板
 */
@Entity
public class SubscriptionTemplate extends BaseEntity<Long> {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private App app;

    @NotEmpty
    @Column(nullable = false,updatable = false)
    private String templateId;

    @Convert(converter = ParamConverter.class)
    private Map<String,Value> param = new HashMap<>();


    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, Value> getParam() {
        return param;
    }

    public void setParam(Map<String, Value> param) {
        this.param = param;
    }

    public static class Value implements Serializable {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Value(){
            this.value = "";
        }
        public Value(String value){
            this.value = value;
        }

    }

    @Converter
    public static class ParamConverter extends BaseAttributeConverter<Map<String,Value>> {

    }
}
