/**
  * Copyright 2020 json.cn 
  */
package com.bootx.vo.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Auto-generated: 2020-11-13 15:16:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class List {

    private Long vod_id;
    private String vod_name;
    private Long type_id;
    private String type_name;
    private String vod_en;
    private String vod_time;
    private String vod_remarks;
    private String vod_play_from;
    public void setVod_id(Long vod_id) {
         this.vod_id = vod_id;
     }
     public Long getVod_id() {
         return vod_id;
     }

    public void setVod_name(String vod_name) {
         this.vod_name = vod_name;
     }
     public String getVod_name() {
         return vod_name;
     }

    public void setType_id(Long type_id) {
         this.type_id = type_id;
     }
     public Long getType_id() {
         return type_id;
     }

    public void setType_name(String type_name) {
         this.type_name = type_name;
     }
     public String getType_name() {
         return type_name;
     }

    public void setVod_en(String vod_en) {
         this.vod_en = vod_en;
     }
     public String getVod_en() {
         return vod_en;
     }

    public void setVod_time(String vod_time) {
         this.vod_time = vod_time;
     }
     public String getVod_time() {
         return vod_time;
     }

    public void setVod_remarks(String vod_remarks) {
         this.vod_remarks = vod_remarks;
     }
     public String getVod_remarks() {
         return vod_remarks;
     }

    public void setVod_play_from(String vod_play_from) {
         this.vod_play_from = vod_play_from;
     }
     public String getVod_play_from() {
         return vod_play_from;
     }

}