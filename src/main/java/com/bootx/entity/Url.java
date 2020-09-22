package com.bootx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Url extends OrderedEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    private PlayUrl playUrl;

    private String title;

    private String url;

    public PlayUrl getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(PlayUrl playUrl) {
        this.playUrl = playUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



