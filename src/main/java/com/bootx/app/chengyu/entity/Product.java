package com.bootx.app.chengyu.entity;

import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "chengyu_product")
public class Product extends BaseEntity<Long> {

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, precision = 27, scale = 12)
    private BigDecimal price;

    @Column(nullable = false, precision = 27, scale = 12)
    private BigDecimal marketPrice;

    @NotEmpty
    @Column(nullable = false)
    private String img;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Long count;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Long remainCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private App app;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Long remainCount) {
        this.remainCount = remainCount;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
