package com.bootx.app.duanshipin.entity;

import com.bootx.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "duanshipin_short_video_channel")
public class ShortVideoChannel extends BaseEntity<Long> {

    private String name;

    private String channelId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
