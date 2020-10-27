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
public class Style {

    private String fontSize;
    private String color;
    public void setFontSize(String fontSize) {
         this.fontSize = fontSize;
     }
     public String getFontSize() {
         return fontSize;
     }

    public void setColor(String color) {
         this.color = color;
     }
     public String getColor() {
         return color;
     }

}