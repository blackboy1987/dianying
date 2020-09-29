/**
 * Copyright 2020 bejson.com
 */
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated: 2020-09-21 19:51:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Entity
public class Movie extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,unique = true,updatable = false)
    private String videoId;

    @JsonView({BaseEntity.ViewView.class})
    private String title;

    @JsonView({BaseEntity.ViewView.class})
    private String img;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonView({BaseEntity.ViewView.class})
    private Set<PlayUrl> playUrls = new HashSet<>();

    /**
     * 演员
     */
    private String actors;

    /**
     * 导演
     */
    private String director;

    /**
     * 区域
     */
    private String area;

    /**
     * 语言
     */
    private String lang;

    /**
     * 评分
     */
    private Double score;

    /**
     * 简介
     */
    @Lob
    private String content;

    /**
     * 文章分类
     */
    @NotNull
    @JsonView(BaseView.class)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieCategory> movieCategories = new HashSet<>();

    /**
     * 文章标签
     */
    @JsonView(BaseView.class)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieTag> movieTags = new HashSet<>();

    private Date updateTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<PlayUrl> getPlayUrls() {
        return playUrls;
    }

    public void setPlayUrls(Set<PlayUrl> playUrls) {
        this.playUrls = playUrls;
    }

    public Set<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(Set<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }

    public Set<MovieTag> getMovieTags() {
        return movieTags;
    }

    public void setMovieTags(Set<MovieTag> movieTags) {
        this.movieTags = movieTags;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}