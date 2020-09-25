package com.bootx.util.addressparse.migua818;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayInfo implements Serializable {

    private String title;

    private List<PlayUrl> playUrls = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PlayUrl> getPlayUrls() {
        return playUrls;
    }

    public void setPlayUrls(List<PlayUrl> playUrls) {
        this.playUrls = playUrls;
    }
}
