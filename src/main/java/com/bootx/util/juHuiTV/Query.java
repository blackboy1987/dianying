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
public class Query {

    private String id;
    private String episode;
    private String host;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setEpisode(String episode) {
         this.episode = episode;
     }
     public String getEpisode() {
         return episode;
     }

    public void setHost(String host) {
         this.host = host;
     }
     public String getHost() {
         return host;
     }

}