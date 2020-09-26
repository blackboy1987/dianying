package com.bootx.util.addressparse.vo;

import com.bootx.util.addressparse.migua818.PlayInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Movie implements Serializable {

    private String link;

    private String title;

    private String img;

    private Set<String> tags = new HashSet<>();

    private String jianjie;

    private String playUrl;

    private String score;

    private String actors;

    private Set<PlayInfo> playInfos = new HashSet<>();

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Set<PlayInfo> getPlayInfos() {
        return playInfos;
    }

    public void setPlayInfos(Set<PlayInfo> playInfos) {
        this.playInfos = playInfos;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
