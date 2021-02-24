package com.bootx.controller.admin;

import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.service.AppService;
import com.bootx.service.SiteInfoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("adminAppController")
@RequestMapping("/admin/api/app")
public class AppController {

    @Resource
    private AppService appService;
    @Resource
    private SiteInfoService siteInfoService;

    @PostMapping("/list")
    @JsonView(BaseEntity.PageView.class)
    public Result list(Pageable pageable){
        return Result.success(appService.findPageJdbc(pageable));
    }


    public Result info(Long id){
        return Result.success(siteInfoService.findJdbc(id));
    }
}
