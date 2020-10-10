/**
 * Copyright 2020 bejson.com
 */
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;

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

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonView({BaseEntity.ViewView.class})
    private Set<DownloadUrl> downloadUrls = new HashSet<>();

    /**
     * 演员
     */
    @JsonView({BaseEntity.ViewView.class})
    private String actors;

    /**
     * 导演
     */
    @JsonView({BaseEntity.ViewView.class})
    private String director;

    /**
     * 区域
     */
    @JsonView({BaseEntity.ViewView.class})
    private String area;

    /**
     * 语言
     */
    @JsonView({BaseEntity.ViewView.class})
    private String lang;

    /**
     * 评分
     */
    @JsonView({BaseEntity.ViewView.class})
    private Double score;

    /**
     * 简介
     */
    @Lob
    @JsonView({BaseEntity.ViewView.class})
    private String content;

    /**
     * 文章分类
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieCategory movieCategory;

    /**
     * 文章标签
     */
    @JsonView(BaseView.class)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieTag> movieTags = new HashSet<>();

    @JsonView({BaseEntity.ViewView.class})
    private Date updateTime;

    /**
     * 上映时间
     */
    @JsonView({BaseEntity.ViewView.class})
    private String year;

    @JsonView({BaseEntity.ViewView.class})
    private String remarks;

    private Integer status;

    /**
     * 别名
     */
    @JsonView({BaseEntity.ViewView.class})
    private String sub;

    /**
     * 英文名
     */
    @JsonView({BaseEntity.ViewView.class})
    private String english;

    /**
     * 首字母
     */
    private String letter;

    private String behind;

    @Length(max = 2000)
    @Column(length = 2000)
    private String blurb;

    @JsonView({BaseEntity.ViewView.class})
    private String serial;

    @NotNull
    @Column(nullable = false)
    private Boolean isShow;


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

    public Set<DownloadUrl> getDownloadUrls() {
        return downloadUrls;
    }

    public void setDownloadUrls(Set<DownloadUrl> downloadUrls) {
        this.downloadUrls = downloadUrls;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getBehind() {
        return behind;
    }

    public void setBehind(String behind) {
        this.behind = behind;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}