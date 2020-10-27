package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class APIConfig extends BaseEntity<Long> {

    @JsonView(BaseView.class)
    @NotNull
    @Column(unique = true,nullable = false)
    private String apiKey;
    @JsonView(BaseView.class)
    @NotNull
    @Column(nullable = false)
    private String apiValue;
    @JsonView(BaseView.class)
    private String apiRemark;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiValue() {
        return apiValue;
    }

    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }

    public String getApiRemark() {
        return apiRemark;
    }

    public void setApiRemark(String apiRemark) {
        this.apiRemark = apiRemark;
    }
}
