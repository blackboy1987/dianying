package com.bootx.member.entity;

import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class MemberRank extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false)
    @JsonView({ViewView.class})
    private String name;

    @NotNull
    @Column(nullable = false)
    private Boolean isDefault;

    /**
     * 消费金额
     */
    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(precision = 21, scale = 6)
    private BigDecimal amount;

    /**
     * 是否特殊
     */
    @NotNull
    @Column(nullable = false)
    private Boolean isSpecial;

    @Column(nullable = false)
    private Long point;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private App app;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
