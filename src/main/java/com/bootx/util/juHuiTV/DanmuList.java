/**
  * Copyright 2020 json.cn 
  */
package com.bootx.util.juHuiTV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Auto-generated: 2020-10-24 22:11:45
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DanmuList {

    private String status;
    private String _id;
    private String content;
    private String programme;
    private String episode;
    private String stime;
    private String color;
    private User user;
    private String size;
    private String mode;
    private String type;
    private long createdAt;
    private long updatedAt;
    private int __v;
    private String id;
    private int duration;
    private String txt;
    private int start;
    private Style style;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setProgramme(String programme) {
         this.programme = programme;
     }
     public String getProgramme() {
         return programme;
     }

    public void setEpisode(String episode) {
         this.episode = episode;
     }
     public String getEpisode() {
         return episode;
     }

    public void setStime(String stime) {
         this.stime = stime;
     }
     public String getStime() {
         return stime;
     }

    public void setColor(String color) {
         this.color = color;
     }
     public String getColor() {
         return color;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public void setSize(String size) {
         this.size = size;
     }
     public String getSize() {
         return size;
     }

    public void setMode(String mode) {
         this.mode = mode;
     }
     public String getMode() {
         return mode;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setCreatedAt(long createdAt) {
         this.createdAt = createdAt;
     }
     public long getCreatedAt() {
         return createdAt;
     }

    public void setUpdatedAt(long updatedAt) {
         this.updatedAt = updatedAt;
     }
     public long getUpdatedAt() {
         return updatedAt;
     }

    public void set__v(int __v) {
         this.__v = __v;
     }
     public int get__v() {
         return __v;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setTxt(String txt) {
         this.txt = txt;
     }
     public String getTxt() {
         return txt;
     }

    public void setStart(int start) {
         this.start = start;
     }
     public int getStart() {
         return start;
     }

    public void setStyle(Style style) {
         this.style = style;
     }
     public Style getStyle() {
         return style;
     }

}