package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Hero extends BaseEntity<Long> {

    @JsonView({ListView.class})
    private String name;

    @JsonView({ListView.class})
    private String headImg;

    @Column(nullable = false,updatable = false,unique = true)
    @JsonView({ListView.class})
    private Long heroId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }
}
