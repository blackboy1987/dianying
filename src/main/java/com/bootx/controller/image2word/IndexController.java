package com.bootx.controller.image2word;

import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Member;
import com.bootx.service.AppService;
import com.bootx.service.MemberService;
import com.bootx.service.api.IndexService;
import com.bootx.util.JWTUtils;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController("image2WordIndexController")
@RequestMapping("/image2word/api")
public class IndexController {

    @Autowired
    private AppService appService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/upload")
    public Map<String,Object> upload(MultipartFile file) throws IOException {
        System.out.println(file);
        Map<String,Object> map = new HashMap<>();

        String destPath = "d:/" + UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File destFile = new File(destPath);
        File destDir = destFile.getParentFile();
        if (destDir != null) {
            destDir.mkdirs();
        }
        file.transferTo(destFile);
        try {
            BufferedImage bufferedImage = ImageIO.read(destFile);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            map.put("width",width);
            map.put("height",height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("result",OCRDemo.AKSKDemo(destFile).getResult());
        return map;

    }

    @GetMapping("/login")
    public com.bootx.common.Result index(String code, String appCode, String appSecret){

        Map<String,Object> data = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        App app = appService.findByAppCode(appCode);
        Map<String,Object> params = new HashMap<>();
        params.put("appid",app.getAppId());
        params.put("secret",app.getAppSecret());
        params.put("js_code",code);
        params.put("grant_type","authorization_code");
        Map<String,String> result = JsonUtils.toObject(WebUtils.get(url, params), new TypeReference<Map<String, String>>() {});
        Member member = memberService.create(result,app);
        if(member!=null){
            Map<String,Object> data1 = new HashMap<>(result);
            data1.put("id",member.getId());
            data.put("id",member.getId());
            data.putAll(memberService.getData(member));
            data.put("token", JWTUtils.create(member.getId()+"",data1));
        }
        return Result.success(data);
    }

    @GetMapping("/site")
    @JsonView(BaseEntity.ViewView.class)
    public Result site(String appCode,String appSecret){
        if(appService.exist(appCode,appSecret)){
            return Result.success(indexService.site(appCode,appSecret));
        }
        return Result.error("不存在");
    }
}
