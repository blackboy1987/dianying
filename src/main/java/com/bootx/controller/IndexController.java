package com.bootx.controller;

import com.bootx.common.Result;
import com.bootx.entity.Level;
import com.bootx.entity.LevelImage;
import com.bootx.service.LevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/level")
    public Result level(){
       List<Level> levels = levelService.findAll();
        for (Level level:levels) {
            List<LevelImage> content = level.getContent();
            for (LevelImage levelImage:content) {
                if(StringUtils.equals("layer",levelImage.getName())){
                    levelImage.setUrl("https://bootx-zhaocha.oss-cn-hangzhou.aliyuncs.com/images/lv/"+level.getId()+"/"+levelImage.getName()+".jpg");
                }else{
                    levelImage.setUrl("https://bootx-zhaocha.oss-cn-hangzhou.aliyuncs.com/images/lv/"+level.getId()+"/"+levelImage.getName()+".png");
                }
            }
            levelService.update(level);

        }
        Map<String,Object> data = new HashMap<>();
        Level level = levelService.find(37L);
        data.put("value",level.getId());
        data.put("question",level.getContent());
        return Result.success(data);
    }

}
