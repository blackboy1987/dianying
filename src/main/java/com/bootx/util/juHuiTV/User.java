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
public class User {

    private String avatar;
    private String _id;
    private String nickname;
    private String id;
    public void setAvatar(String avatar) {
         this.avatar = avatar;
     }
     public String getAvatar() {
         return avatar;
     }

    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

}