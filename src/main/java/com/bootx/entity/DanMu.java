
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class DanMu extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,updatable = false)
    private String videoId;


    @NotNull
    @Column(nullable = false,updatable = false)
    private String playUrlKey;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({ViewView.class})
    private String text;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({ViewView.class})
    private String color;

    @NotNull
    @Column(nullable = false,unique = true,updatable = false)
    @JsonView({ViewView.class})
    private float time;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPlayUrlKey() {
        return playUrlKey;
    }

    public void setPlayUrlKey(String playUrlKey) {
        this.playUrlKey = playUrlKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}