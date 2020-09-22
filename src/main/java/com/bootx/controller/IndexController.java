package com.bootx.controller;

import cn.hutool.core.date.DateUtil;
import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.entity.Url;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.MovieService;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private MovieCategoryService movieCategoryService;

    /**
     * kong:https://www.i-gomall.com/app/index.php?i=2&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=
     * https://www.i-gomall.com/app/index.php?i=2&t=21&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&page=1
     * 连续剧：https://www.i-gomall.com/app/index.php?i=2&t=2&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 综艺   https://www.i-gomall.com/app/index.php?i=2&t=3&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 动漫： https://www.i-gomall.com/app/index.php?i=2&t=4&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 动作片https://www.i-gomall.com/app/index.php?i=2&t=20&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 喜剧片 https://www.i-gomall.com/app/index.php?i=2&t=21&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 科幻片 https://www.i-gomall.com/app/index.php?i=2&t=23&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 爱情片 https://www.i-gomall.com/app/index.php?i=2&t=22&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 恐怖片 https://www.i-gomall.com/app/index.php?i=2&t=24&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 剧情片 https://www.i-gomall.com/app/index.php?i=2&t=25&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 战争片 https://www.i-gomall.com/app/index.php?i=2&t=26&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 国产片 https://www.i-gomall.com/app/index.php?i=2&t=27&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 港台剧 https://www.i-gomall.com/app/index.php?i=2&t=28&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 日韩剧 https://www.i-gomall.com/app/index.php?i=2&t=29&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * 欧美剧 https://www.i-gomall.com/app/index.php?i=2&t=30&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=1
     * @return
     */


    @GetMapping("/index1")
    public String index1 () {
        long start = System.currentTimeMillis();
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        for (MovieCategory mo:movieCategories) {
            System.out.println(mo.getCategoryId()+"==========================================================================================开始");
            boolean flag = true;
            String url = "https://www.i-gomall.com/app/index.php?i=2&t="+mo.getCategoryId()+"&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=";
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
                                parseUrl(movie);
                                movie.setMovieCategory(mo);
                                movieService.save(movie);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    System.out.println(mo.getCategoryId()+"================="+String.format("===========================" + index + ":" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
                    index = index+1;
                }else{
                    flag = false;
                }
            }

            System.out.println(mo.getCategoryId()+"===============================================结束");
        }

        System.out.println("end===="+(System.currentTimeMillis()-start)/1000);
        return "ok";

    }



    @GetMapping("/category")
    public String movieCategory(){
        // 电影
        MovieCategory movieCategory = new MovieCategory();
        movieCategory.setName("电影");
        movieCategory.setCategoryId(1L);
        movieCategoryService.save(movieCategory);
        // 连续剧
        MovieCategory movieCategory1 = new MovieCategory();
        movieCategory1.setName("连续剧");
        movieCategory1.setCategoryId(2L);
        movieCategoryService.save(movieCategory1);
        // 综艺
        MovieCategory movieCategory2 = new MovieCategory();
        movieCategory2.setName("综艺");
        movieCategory2.setCategoryId(3L);
        movieCategoryService.save(movieCategory2);
        // 动漫
        MovieCategory movieCategory3 = new MovieCategory();
        movieCategory3.setName("动漫");
        movieCategory3.setCategoryId(4L);
        movieCategoryService.save(movieCategory3);
        // 动漫
        MovieCategory movieCategory4 = new MovieCategory();
        movieCategory4.setName("动作片");
        movieCategory4.setCategoryId(20L);
        movieCategoryService.save(movieCategory4);
        // 动漫
        MovieCategory movieCategory5 = new MovieCategory();
        movieCategory5.setName("喜剧片");
        movieCategory5.setCategoryId(21L);
        movieCategoryService.save(movieCategory5);
        // 动漫
        MovieCategory movieCategory6 = new MovieCategory();
        movieCategory6.setCategoryId(22L);
        movieCategory6.setName("爱情片");
        movieCategoryService.save(movieCategory6);
        // 动漫
        MovieCategory movieCategory7 = new MovieCategory();
        movieCategory7.setCategoryId(23L);
        movieCategory7.setName("科幻片");
        movieCategoryService.save(movieCategory7);
        // 动漫
        MovieCategory movieCategory8 = new MovieCategory();
        movieCategory8.setCategoryId(24L);
        movieCategory8.setName("恐怖片");
        movieCategoryService.save(movieCategory8);
        // 动漫
        MovieCategory movieCategory9 = new MovieCategory();
        movieCategory9.setName("剧情片");
        movieCategory9.setCategoryId(25L);
        movieCategoryService.save(movieCategory9);
        // 动漫
        MovieCategory movieCategory10 = new MovieCategory();
        movieCategory10.setName("战争片");
        movieCategory10.setCategoryId(26L);
        movieCategoryService.save(movieCategory10);
        // 动漫
        MovieCategory movieCategory11 = new MovieCategory();
        movieCategory11.setName("国产片");
        movieCategory11.setCategoryId(27L);
        movieCategoryService.save(movieCategory11);
        // 动漫
        MovieCategory movieCategory12 = new MovieCategory();
        movieCategory12.setName("港台剧");
        movieCategory12.setCategoryId(28L);
        movieCategoryService.save(movieCategory12);
        // 动漫
        MovieCategory movieCategory13 = new MovieCategory();
        movieCategory13.setName("日韩剧");
        movieCategory13.setCategoryId(29L);
        movieCategoryService.save(movieCategory13);
        // 动漫
        MovieCategory movieCategory14 = new MovieCategory();
        movieCategory14.setName("欧美剧");
        movieCategory14.setCategoryId(30L);
        movieCategoryService.save(movieCategory14);
        return "abc";
    }
















    private void parseUrl(Movie movie) {
        String vodPlayUrl = movie.getVod_play_url();
        String regEx="[$]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(vodPlayUrl);
        String result = m.replaceAll("=");
        String[] playUrlsArray = result.split("===");
        int index = 1;
        for (String u:playUrlsArray) {
            PlayUrl playUrl = new PlayUrl();
            playUrl.setMovie(movie);
            playUrl.setTitle("线路"+index);
            String[] jishusArray = u.split("#");
            for (String jishu:jishusArray) {
                String[] aa = jishu.split("=");
                Url url = new Url();
                url.setPlayUrl(playUrl);

                if(aa.length==2){
                    url.setTitle(aa[0]);
                    url.setUrl(aa[1]);
                    try{
                        url.setOrder(Integer.valueOf(aa[0].replaceAll("第","").replaceAll("集","")));
                    }catch (Exception e){
                        // e.printStackTrace();
                        url.setOrder(null);
                    }
                }else{
                    url.setUrl(aa[0]);
                }
                playUrl.getUrls().add(url);

            }
            movie.getPlayUrls().add(playUrl);
        }

    }
}
