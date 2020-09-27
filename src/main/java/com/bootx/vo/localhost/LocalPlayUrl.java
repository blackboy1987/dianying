package com.bootx.vo.localhost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalPlayUrl implements Serializable {

    @JsonProperty("flag_name")
    private String name;

    private Integer part;

    private List<String> video = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public List<String> getVideo() {
        video.sort((i,j)->i.compareTo(j));
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }
}
