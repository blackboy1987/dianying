package com.bootx.app.tuzhuanwen;

import org.apache.commons.io.FilenameUtils;
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


    @PostMapping("/upload")
    public Map<String,Object> upload(MultipartFile file) throws IOException {
        System.out.println(file);
        Map<String,Object> map = new HashMap<>();

        String destPath = "/file/"+UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
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
}
