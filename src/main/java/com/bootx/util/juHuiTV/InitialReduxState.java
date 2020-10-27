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
public class InitialReduxState {

    private int lastUpdate;
    private boolean light;
    private int count;
    public void setLastUpdate(int lastUpdate) {
         this.lastUpdate = lastUpdate;
     }
     public int getLastUpdate() {
         return lastUpdate;
     }

    public void setLight(boolean light) {
         this.light = light;
     }
     public boolean getLight() {
         return light;
     }

    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

}