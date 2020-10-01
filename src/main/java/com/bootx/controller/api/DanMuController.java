package com.bootx.controller.api;

import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.DanMu;
import com.bootx.service.DanMuService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("apiDanMuController")
@RequestMapping("/api/danmu")
public class DanMuController {

    @Autowired
    private DanMuService danMuService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/save")
    @JsonView(BaseEntity.ListView.class)
    public Result save(DanMu danMu){
        danMuService.save(danMu);
        return Result.success("ok");
    }

    @GetMapping("/list")
    @JsonView(BaseEntity.ListView.class)
    public Result list(Long videoId){
        return Result.success(jdbcTemplate.queryForList("select text,color,time from danmu where videoId=? order by time asc ",videoId));
    }
}
