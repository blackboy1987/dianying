package com.bootx.app.duanshipin.entity;

import com.bootx.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "duanshipin_short_video")
public class ShortVideo extends BaseEntity<Long> {

    @NotEmpty
    @Column(nullable = false,updatable = false,unique = true)
    private String videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShortVideoChannel shortVideoChannel;

    private String title;

    private String channelId;

    private String description;

    private Long duration;

    private String time;

    private String channelName;

    private String videoImage;

    private String videoUrl;

    private Long uploadTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public ShortVideoChannel getShortVideoChannel() {
        return shortVideoChannel;
    }

    public void setShortVideoChannel(ShortVideoChannel shortVideoChannel) {
        this.shortVideoChannel = shortVideoChannel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
