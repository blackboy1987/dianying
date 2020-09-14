package com.bootx.miniprogram.entity;

import com.bootx.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "zhaocha_member_rank")
public class MemberRank extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,unique = true)
    @JsonView({BaseEntity.ViewView.class})
    private String name;

    @NotNull
    @Column(nullable = false)
    @JsonView({BaseEntity.ViewView.class})
    private String title;

    @NotNull
    @Column(nullable = false)
    @JsonView({BaseEntity.ViewView.class})
    private Integer gold;

    @NotNull
    @Column(nullable = false)
    private Boolean isDefault;

    @NotNull
    @Min(0)
    @Column(nullable = false,unique = true)
    private Integer level;












    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Transient
    @JsonView({BaseEntity.ViewView.class})
    public String getImg(){
        return "https://bootx-xxl.oss-cn-hangzhou.aliyuncs.com/zhaocha/rank/dg"+getId()+".png";
    }
    @Transient
    @JsonView({BaseEntity.ViewView.class})
    public String getRankImg(){
        return "https://bootx-xxl.oss-cn-hangzhou.aliyuncs.com/zhaocha/rank/rank"+getId()+".png";
    }
}
