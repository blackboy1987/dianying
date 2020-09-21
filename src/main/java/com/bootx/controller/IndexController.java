package com.bootx.controller;

import cn.hutool.core.date.DateUtil;
import com.bootx.entity.Movie;
import com.bootx.service.MovieService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class IndexController {

    /**
     * 电影：1027208b869c87eda171c4a80706b426
     * 电视剧：1027208b869c87eda171c4a80706b426
     * 综艺：1027208b869c87eda171c4a80706b426
     * 动漫：1027208b869c87eda171c4a80706b426
     * 动作片：1027208b869c87eda171c4a80706b426
     * 喜剧片：1027208b869c87eda171c4a80706b426
     * 爱情片：1027208b869c87eda171c4a80706b426
     * 科幻片：1027208b869c87eda171c4a80706b426
     * 恐怖片：1027208b869c87eda171c4a80706b426
     * 剧情片：1027208b869c87eda171c4a80706b426
     * 战争篇：1027208b869c87eda171c4a80706b426
     * 国产片：1027208b869c87eda171c4a80706b426
     * 港台剧：1027208b869c87eda171c4a80706b426
     * 日韩剧：1027208b869c87eda171c4a80706b426
     * 欧美剧：1027208b869c87eda171c4a80706b426
     */



    @Autowired
    private MovieService movieService;

    /**
     * 连续剧：https://www.i-gomall.com/app/index.php?i=2&t=2&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 综艺：https://www.i-gomall.com/app/index.php?i=2&t=3&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 动漫：https://www.i-gomall.com/app/index.php?i=2&t=4&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 动作片：https://www.i-gomall.com/app/index.php?i=2&t=20&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 喜剧片：https://www.i-gomall.com/app/index.php?i=2&t=21&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 科幻片：https://www.i-gomall.com/app/index.php?i=2&t=23&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 爱情片：https://www.i-gomall.com/app/index.php?i=2&t=22&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 恐怖片：https://www.i-gomall.com/app/index.php?i=2&t=24&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 剧情片：https://www.i-gomall.com/app/index.php?i=2&t=25&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 战争片：https://www.i-gomall.com/app/index.php?i=2&t=26&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 国产片：https://www.i-gomall.com/index.php?i=2&t=27&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 港台剧：https://www.i-gomall.com/app/index.php?i=2&t=28&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 日韩剧：https://www.i-gomall.com/app/index.php?i=2&t=29&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 欧美剧：https://www.i-gomall.com/app/index.php?i=2&t=30&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * @return
     */


    @GetMapping("/index1")
    public String index1 (int type) {
        boolean flag = true;
        String url = "https://bg.zqbkk.cn/app/index.php?i=2&t="+type+"&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&page=";

        int index = 1;
        while (flag){
            String result = WebUtils.get(url+index,null);
            JsonRootBean jsonRootBean = JsonUtils.toObject(result,JsonRootBean.class);
            if(jsonRootBean.getData().size()>0){
                new Thread(()->{
                    for (Data data : jsonRootBean.getData()) {
                        try {
                            Movie movie = new Movie();
                            BeanUtils.copyProperties(data,movie);
                            movieService.save(movie);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                System.out.println(String.format("=============================" + index + ":" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
                index = index+1;
            }else{
                flag = false;
            }
        }
        return "ok";

    }
}
