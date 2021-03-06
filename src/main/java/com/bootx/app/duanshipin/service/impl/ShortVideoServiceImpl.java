
package com.bootx.app.duanshipin.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bootx.app.duanshipin.dao.ShortVideoDao;
import com.bootx.app.duanshipin.entity.ShortVideo;
import com.bootx.app.duanshipin.entity.ShortVideoChannel;
import com.bootx.app.duanshipin.pojo.JsonRootBean;
import com.bootx.app.duanshipin.pojo.JsonRootBean1;
import com.bootx.app.duanshipin.pojo.RetStr;
import com.bootx.app.duanshipin.service.ShortVideoChannelService;
import com.bootx.app.duanshipin.service.ShortVideoService;
import com.bootx.service.impl.BaseServiceImpl;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class ShortVideoServiceImpl extends BaseServiceImpl<ShortVideo, Long> implements ShortVideoService {

    @Resource
    private ShortVideoChannelService shortVideoChannelService;

    @Resource
    private ShortVideoDao shortVideoDao;

    @Override
    public void sync(Integer start,Integer end){
        List<ShortVideoChannel> list = shortVideoChannelService.findAll();
        for (int i = start; i < end; i++) {
            for (ShortVideoChannel shortVideoChannel:list) {
                sync(shortVideoChannel,i);
            }
        }
    }

    @Override
    public boolean exist(String videoId) {
        return shortVideoDao.exists("videoId",videoId);
    }

    private void sync(ShortVideoChannel shortVideoChannel,Integer page){
        String s = WebUtils.get("https://weixinapi.baomihua.com/getrecommend.ashx?dataType=rec&pageIndex="+page+"&pageSize=5&sceneType=weixin_channel&clientId=&channelId="+shortVideoChannel.getChannelId()+"&wxnum=1000&pmhUserId=&version=v7.0.4",null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s, JsonRootBean.class);
        System.out.println(shortVideoChannel.getName()+"==================================="+page+"==================="+jsonRootBean.getCount());
        List<RetStr> retStr = jsonRootBean.getRetStr();
        for (RetStr item:retStr){
            if(exist(item.getVideoid())){
                System.out.println(item.getVideoid()+"=============================================存在");
                continue;
            }

            ShortVideo shortVideo = new ShortVideo();
            shortVideo.setShortVideoChannel(shortVideoChannel);
            shortVideo.setChannelName(shortVideoChannel.getName());
            shortVideo.setChannelId(shortVideoChannel.getChannelId());
            shortVideo.setTime(item.getTime());
            shortVideo.setVideoId(item.getVideoid());
            shortVideo.setTitle(item.getVideoname());
            shortVideo.setVideoImage(item.getVideoimgurl());
            String s1 = WebUtils.get("https://weixinapi.baomihua.com/getvideourl.aspx?flvid="+shortVideo.getVideoId()+"&devicetype=wap&dataType=json", null);
            System.out.println(s1);
            JsonRootBean1 jsonRootBean1 = JsonUtils.toObject(s1,JsonRootBean1.class);
            if(jsonRootBean1.getAlipalyurl().indexOf("http")==0){
                shortVideo.setVideoUrl(jsonRootBean1.getAlipalyurl());
            }else{
                shortVideo.setVideoUrl("https://"+jsonRootBean1.getAlipalyurl());
            }
            try{
                shortVideo.setUploadTime(new Date(jsonRootBean1.getUploadTime()*1000));
            }catch (Exception e){
                e.printStackTrace();
            }
            shortVideo.setDuration(jsonRootBean1.getTotaltime());
            shortVideo.setSize(jsonRootBean1.getVideofilesize());
            super.save(shortVideo);
           /* try {
                upload(shortVideo.getVideoImage(),shortVideo.getVideoId());
                new Thread(()->{
                    try {
                        upload(shortVideo.getVideoUrl(),shortVideo.getVideoId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }


    private static void upload(String url,String filePath,String fileName) throws IOException {;
        String extension = FilenameUtils.getExtension(url);
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4GCjrkxGi8rcyoiy6i8Y";
        String accessKeySecret = "AEG4gBrjvNQvSJRSStrZfHfC4EJZOW";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println(url);
        InputStream inputStream = new URL(url).openStream();
        ossClient.putObject("bootx-video", filePath+"/"+ fileName +"."+extension, inputStream);
        ossClient.shutdown();
    }

    public static void main(String[] args) throws IOException {
        upload("https://img.baomihua.com/v1/head/3f5b7770-15c9-4d99-8ada-5850a42d1388_61562606.jpg","1234","345");
    }
}