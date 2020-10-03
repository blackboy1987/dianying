package com.bootx.service.api;

import com.bootx.entity.Movie;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;

import java.io.IOException;
import java.util.Map;

public interface IndexService {

    Map<String,Object> site(String appCode, String appSecret);

    Object list(Long categoryId,Integer pageNumber);

    void updateResource(String keywords);

    Movie save(Data data);

    void es() throws IOException;
}
