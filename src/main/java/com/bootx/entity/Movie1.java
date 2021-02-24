package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * {
 *             "vod_id": 5970,
 *             "vod_name": "王子2014",
 *             "type_id": 6,
 *             "type_name": "动作片",
 *             "vod_en": "wangzi2014",
 *             "vod_time": "2020-11-11 13:15:14",
 *             "vod_remarks": "HD高清",
 *             "vod_play_from": "kuyun,ckm3u8"
 *         }
 */
@Entity
public class Movie1 extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,unique = true,updatable = false)
    private String videoId;

    @JsonView({ViewView.class})
    private String title;

    /**
     * 英文名
     */
    @JsonView({ViewView.class})
    private String english;

    /**
     * 更新时间
     */
    private Date time;

    /**
     * 更新时间(同time)
     */
    private Date updateTime;

    /**
     * 添加时间
     */
    private Date addTime;

    @JsonView({ViewView.class})
    private String remarks;

    private String playFrom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieCategory movieCategory;

    private String sub;

    @JsonView({ViewView.class})
    private String pic;

    @JsonView({ViewView.class})
    private String actor;

    @JsonView({ViewView.class})
    private String director;

    @JsonView({ViewView.class})
    private String area;

    @JsonView({ViewView.class})
    private String lang;

    @JsonView({ViewView.class})
    private String year;


    @Lob
    @JsonView({ViewView.class})
    private String content;

    /**
     * 简介
     */
    @JsonView({ViewView.class})
    private String blurb;

    @OneToMany(mappedBy = "movie1",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonView({BaseEntity.ViewView.class})
    private Set<PlayUrl> playUrls = new HashSet<>();

    @OneToMany(mappedBy = "movie1",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonView({BaseEntity.ViewView.class})
    private Set<DownloadUrl> downloadUrls = new HashSet<>();

    @JsonView({ViewView.class})
    private Double score;

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

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPlayFrom() {
        return playFrom;
    }

    public void setPlayFrom(String playFrom) {
        this.playFrom = playFrom;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}