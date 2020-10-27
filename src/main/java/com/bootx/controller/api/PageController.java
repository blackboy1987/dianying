package com.bootx.controller.api;

import com.bootx.common.Result;
import com.bootx.entity.APIConfig;
import com.bootx.service.APIConfigService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController("cmsIndexController")
@RequestMapping("/common/api")
@CrossOrigin
public class PageController {

    @Autowired
    private APIConfigService apiConfigService;

    @PostMapping(value="/index")
    public String index(HttpServletRequest request, @RequestBody(required = false) Map<String,String> map){
        String baseModel = request.getHeader("basemodel");
        String url = map.get("url");
        if(StringUtils.isEmpty(baseModel)){
            return JsonUtils.toJson(Result.error("请求头没设置basemodel"));
        }
        if(StringUtils.isEmpty(url)){
            return JsonUtils.toJson(Result.error("请求参数没有url参数"));
        }
        APIConfig apiConfig = apiConfigService.findByApiKey(baseModel);
        if(apiConfig!=null){
            return WebUtils.post2(apiConfig.getApiValue()+url,map);
        }else{
            return JsonUtils.toJson(Result.error("APIConfig 没有配置 "+baseModel+" 的值"));
        }
    }
}
