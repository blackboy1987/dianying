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
public class ClarityArr {

    private String hash;
    private long size;
    private int display;
    private String displayName;
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

}