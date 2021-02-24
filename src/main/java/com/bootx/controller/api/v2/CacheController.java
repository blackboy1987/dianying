package com.bootx.controller.api.v2;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("apiV2CacheController")
@RequestMapping("/api/v2/cache")
public class CacheController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/clear")
    public String clear(String cacheName){
        stringRedisTemplate.delete(cacheName);
        return "ok";
    }

}
