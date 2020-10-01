/**
  * Copyright 2020 bejson.com 
  */
package com.bootx.vo.okzy.com.jsoncn.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2020-10-01 12:16:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int code;
    private String msg;
    private int page;
    private int pagecount;
    private String limit;
    private int total;

    @JsonProperty("list")
    private List<Data> data = new ArrayList<>();

    @JsonProperty("class")
    private List<Category> category;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setPagecount(int pagecount) {
         this.pagecount = pagecount;
     }
     public int getPagecount() {
         return pagecount;
     }

    public void setLimit(String limit) {
         this.limit = limit;
     }
     public String getLimit() {
         return limit;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setCategory(List<Category> category) {
         this.category = category;
     }
     public List<Category> getCategory() {
         return category;
     }

}