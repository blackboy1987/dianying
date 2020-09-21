package com.bootx.controller;

import com.bootx.entity.Movie;
import com.bootx.service.MovieService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/index")
    public String index (Integer page) {
        String url = "https://www.i-gomall.com/app/index.php?i=2&t=1&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page="+page;
        String result = WebUtils.get(url,null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(result,JsonRootBean.class);
        if(jsonRootBean.getData().size()>0) {
            for (Data data : jsonRootBean.getData()) {
                try {
                    Movie movie = new Movie();
                    movie.setName(data.getVod_name());
                    movieService.save(movie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "ok";

    }


    /*@GetMapping("/index1")
    public String index1 () {
        boolean flag = true;
        String url = "https://bg.zqbkk.cn/app/index.php?i=2&t=1&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&page=";
        int index = 3248;
        while (flag){
            String result = WebUtils.get(url+index,null);
            JsonRootBean jsonRootBean = JsonUtils.toObject(result,JsonRootBean.class);
            if(jsonRootBean.getData().size()>0){
                for (Data data:jsonRootBean.getData()) {
                    try {
                        dataService.save(data);
                    }catch (Exception e){
                        // e.printStackTrace();
                    }
                }
                System.out.println(String.format("=============================" + index + ":" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
                index = index+1;
            }else{
                flag = false;
            }
        }
        return "ok";

    }*/
}
