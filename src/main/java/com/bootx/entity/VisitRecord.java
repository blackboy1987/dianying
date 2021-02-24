package com.bootx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class VisitRecord extends BaseEntity<Long>{

    @NotNull
    @Column(nullable = false,updatable = false)
    private Long memberId;

    @NotNull
    @Column(nullable = false,updatable = false)
    private Long movieId;

    @NotNull
    @Column(nullable = false,updatable = false)
    private String img ;

    @NotNull
    @Column(nullable = false,updatable = false)
    private String title;

    @Column(updatable = false)
    private String lang;

    @NotNull
    @Column(nullable = false,updatable = false)
    private String categoryName;
    
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
