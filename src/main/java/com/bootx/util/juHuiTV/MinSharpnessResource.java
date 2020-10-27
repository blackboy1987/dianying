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
public class MinSharpnessResource {

    private String hash;
    private long size;
    private int display;
    private String displayName;
    private int episode;
    private int duration;
    private String id;
    private int integral;
    private List<ClarityArr> clarityArr;
    private int viewType;
    public void setHash(String hash) {
         this.hash = hash;
     }
     public String getHash() {
         return hash;
     }

    public void setSize(long size) {
         this.size = size;
     }
     public long getSize() {
         return size;
     }

    public void setDisplay(int display) {
         this.display = display;
     }
     public int getDisplay() {
         return display;
     }

    public void setDisplayName(String displayName) {
         this.displayName = displayName;
     }
     public String getDisplayName() {
         return displayName;
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

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setIntegral(int integral) {
         this.integral = integral;
     }
     public int getIntegral() {
         return integral;
     }

    public void setClarityArr(List<ClarityArr> clarityArr) {
         this.clarityArr = clarityArr;
     }
     public List<ClarityArr> getClarityArr() {
         return clarityArr;
     }

    public void setViewType(int viewType) {
         this.viewType = viewType;
     }
     public int getViewType() {
         return viewType;
     }

}