package com.bootx.app.duanshipin.controller;

import com.bootx.app.duanshipin.service.ShortVideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("shortVideoIndexController")
@RequestMapping("/short_video")
public class IndexController {

    @Resource
    private ShortVideoService shortVideoService;

    @GetMapping("/init")
    public String init(){
        shortVideoService.sync(1,200);

        return "ok";
    }

}
