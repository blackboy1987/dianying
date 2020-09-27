package com.bootx.vo.localhost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalResponse implements Serializable {

    private int success;

    private int code;

    private List<LocalMovie> info = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<LocalMovie> getInfo() {
        return info;
    }

    public void setInfo(List<LocalMovie> info) {
        this.info = info;
    }
}
