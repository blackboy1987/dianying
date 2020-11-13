package com.bootx.service.api;

import java.util.Map;

public interface IndexService {

    Map<String,Object> site(String appCode, String appSecret);

    Object list(Long categoryId,Integer pageNumber);
}
