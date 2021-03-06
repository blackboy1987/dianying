/**
  * Copyright 2021 json.cn 
  */
package com.bootx.app.duanshipin.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2021-03-06 15:3:6
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRootBean {

    private List<RetStr> retStr;
    private Date CacheTime;
    private String Count;
    public void setRetStr(List<RetStr> retStr) {
         this.retStr = retStr;
     }
     public List<RetStr> getRetStr() {
         return retStr;
     }

    public void setCacheTime(Date CacheTime) {
         this.CacheTime = CacheTime;
     }
     public Date getCacheTime() {
         return CacheTime;
     }

    public void setCount(String Count) {
         this.Count = Count;
     }
     public String getCount() {
         return Count;
     }

}