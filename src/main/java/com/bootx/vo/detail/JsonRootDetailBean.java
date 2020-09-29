/**
  * Copyright 2020 bejson.com 
  */
package com.bootx.vo.detail;

import com.bootx.vo.Data;

/**
 * Auto-generated: 2020-09-29 16:27:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootDetailBean {

    private int errno;
    private String message;
    private Data data;
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

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

}