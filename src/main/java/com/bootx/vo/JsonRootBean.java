/**
  * Copyright 2020 bejson.com 
  */
package com.bootx.vo;
import java.util.List;

/**
 * Auto-generated: 2020-09-21 19:51:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int errno;
    private String message;
    private List<Data> data;
    public void setErrno(int errno) {
         this.errno = errno;
     }
     public int getErrno() {
         return errno;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}