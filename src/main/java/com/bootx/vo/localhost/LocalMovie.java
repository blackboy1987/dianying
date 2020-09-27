package com.bootx.vo.localhost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalMovie implements Serializable {

    private String title;

    private String pic;

    private Long id;

    private String videoId;

    private String type;

    private Set<LocalPlayUrl> info = new HashSet<>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoId() {
        return "local_"+id;
    }

    public void setVideoId(String videoId) {
        if(StringUtils.isEmpty(videoId)){
            videoId = "local_"+id;
        }
        this.videoId = videoId;
    }

    public Set<LocalPlayUrl> getInfo() {
        return info;
    }

    public void setInfo(Set<LocalPlayUrl> info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
