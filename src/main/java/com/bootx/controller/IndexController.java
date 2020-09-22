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

import java.util.Date;
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
        for (int type=1;type<=100;type++){
            System.out.println(type+"================================================================================================================================================================================================开始");
            boolean flag = true;
            String url = "https://www.i-gomall.com/app/index.php?i=2&t="+type+"&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=";
            if(type==100){
                url = "https://www.i-gomall.com/app/index.php?i=2&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426&page=";
            }
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
                                movieService.save(movie);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    System.out.println(type+"============================="+String.format("===============================================================================================" + index + ":" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
                    index = index+1;
                }else{
                    flag = false;
                }
            }

            System.out.println(type+"=================================================================================================================================================================结束");
        }

        System.out.println("end===="+(System.currentTimeMillis()-start)/1000);
        return "ok";

    }



    @GetMapping("/category")
    public String movieCategory(){
        // 语言
        MovieCategory movieCategory = new MovieCategory();
        movieCategory.setName("语言");
        movieCategory.setFullName(null);
        movieCategory.setTreePath(null);
        movieCategory.setGrade(null);
        movieCategory.setChildren(null);
        movieCategory.setOrder(null);
        movieCategoryService.save(movieCategory);
        // 类型
        MovieCategory movieCategory1 = new MovieCategory();
        movieCategory1.setName("类型");
        movieCategoryService.save(movieCategory1);
        // 地区
        MovieCategory movieCategory2 = new MovieCategory();
        movieCategory2.setName("地区");
        movieCategoryService.save(movieCategory2);
        // 上映时间
        MovieCategory movieCategory3 = new MovieCategory();
        movieCategory3.setName("上映时间");
        movieCategoryService.save(movieCategory3);
        return "abc";
    }
















    private void parseUrl(Movie movie) {
        // 语言：1
       String vodLang = movie.getVod_lang();
        MovieCategory lang = movieCategoryService.find(1L);
        if(StringUtils.isNotEmpty(vodLang)){
            MovieCategory langChild = movieCategoryService.findByName(vodLang);
            if(langChild==null){
                langChild = new MovieCategory();
                langChild.setName(vodLang);
                langChild.setParent(lang);
                langChild = movieCategoryService.save(langChild);
            }
        }



        // 类型：2
       String vodType = movie.getType_name();
        MovieCategory type = movieCategoryService.find(1L);

        if(StringUtils.isNotEmpty(vodType)){
            MovieCategory langChild = movieCategoryService.findByName(vodType);
            if(langChild==null){
                langChild = new MovieCategory();
                langChild.setName(vodType);
                langChild.setParent(type);
                langChild = movieCategoryService.save(langChild);
            }
        }



        // 地区：3
       String vodArea = movie.getVod_area();
        MovieCategory area = movieCategoryService.find(1L);
        if(StringUtils.isNotEmpty(vodArea)){
            MovieCategory langChild = movieCategoryService.findByName(vodArea);
            if(langChild==null){
                langChild = new MovieCategory();
                langChild.setName(vodArea);
                langChild.setParent(area);
                langChild = movieCategoryService.save(langChild);
            }
        }
        // 上映时间：4
        String vodYear = movie.getVod_year();
        MovieCategory year = movieCategoryService.find(1L);
        if(StringUtils.isNotEmpty(vodYear)){
            MovieCategory langChild = movieCategoryService.findByName(vodYear);
            if(langChild==null){
                langChild = new MovieCategory();
                langChild.setName(vodYear);
                langChild.setParent(year);
                langChild = movieCategoryService.save(langChild);
            }
        }











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
                url.setTitle(aa[0]);
                try{
                    url.setOrder(Integer.valueOf(aa[0].replaceAll("第","").replaceAll("集","")));
                }catch (Exception e){
                    e.printStackTrace();
                    url.setOrder(null);
                }
                url.setUrl(aa[1]);

                playUrl.getUrls().add(url);

            }
            movie.getPlayUrls().add(playUrl);
        }

    }
}
