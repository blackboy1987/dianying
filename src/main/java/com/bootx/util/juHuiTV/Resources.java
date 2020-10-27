/**
  * Copyright 2020 json.cn 
  */
package com.bootx.util.juHuiTV;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Auto-generated: 2020-10-24 22:11:45
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resources {

    private String id;
    private List<Data> data;
    private int episode;
    private int duration;
    private boolean owned;
    private int integral;
    private List<Integer> theThemeSongTime;
    private List<Integer> postludeTime;
    private int viewType;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setEpisode(int episode) {
         this.episode = episode;
     }
     public int getEpisode() {
         return episode;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setOwned(boolean owned) {
         this.owned = owned;
     }
     public boolean getOwned() {
         return owned;
     }

    public void setIntegral(int integral) {
         this.integral = integral;
     }
     public int getIntegral() {
         return integral;
     }

    public void setTheThemeSongTime(List<Integer> theThemeSongTime) {
         this.theThemeSongTime = theThemeSongTime;
     }
     public List<Integer> getTheThemeSongTime() {
         return theThemeSongTime;
     }

    public void setPostludeTime(List<Integer> postludeTime) {
         this.postludeTime = postludeTime;
     }
     public List<Integer> getPostludeTime() {
         return postludeTime;
     }

    public void setViewType(int viewType) {
         this.viewType = viewType;
     }
     public int getViewType() {
         return viewType;
     }

}