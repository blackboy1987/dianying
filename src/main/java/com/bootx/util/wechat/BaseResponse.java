package com.bootx.util.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class BaseResponse implements Serializable {

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("errcode")
    private Integer errCode;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
