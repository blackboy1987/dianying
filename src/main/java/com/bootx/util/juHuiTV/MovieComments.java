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
public class MovieComments {

    private String _id;
    private String status;
    private String content;
    private User user;
    private String programme;
    private String type;
    private long createdAt;
    private long updatedAt;
    private int __v;
    private List<String> applyComments;
    private String id;
    private int likeCount;
    private int preference;
    private boolean isBlock;
    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public void setProgramme(String programme) {
         this.programme = programme;
     }
     public String getProgramme() {
         return programme;
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

    public void setApplyComments(List<String> applyComments) {
         this.applyComments = applyComments;
     }
     public List<String> getApplyComments() {
         return applyComments;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setLikeCount(int likeCount) {
         this.likeCount = likeCount;
     }
     public int getLikeCount() {
         return likeCount;
     }

    public void setPreference(int preference) {
         this.preference = preference;
     }
     public int getPreference() {
         return preference;
     }

    public void setIsBlock(boolean isBlock) {
         this.isBlock = isBlock;
     }
     public boolean getIsBlock() {
         return isBlock;
     }

}