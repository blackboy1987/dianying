package com.bootx.service;

public interface RedisService {

    void set(String key,String value);

    String get(String key);

    Boolean remove(String key);

}
