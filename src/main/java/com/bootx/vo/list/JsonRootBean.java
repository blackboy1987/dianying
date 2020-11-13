/**
  * Copyright 2020 json.cn 
  */
package com.bootx.vo.list;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Auto-generated: 2020-11-13 15:16:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRootBean {

    private int code;
    private String msg;
    private int page;
    private int pagecount;
    private String limit;
    private int total;
    private List<com.bootx.vo.list.List> list;
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

    public void setList(List<com.bootx.vo.list.List> list) {
         this.list = list;
     }
     public List<com.bootx.vo.list.List> getList() {
         return list;
     }

}