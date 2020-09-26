package com.bootx.util.addressparse.lclz;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.bootx.util.addressparse.vo.Movie;
import com.bootx.util.addressparse.vo.Tag;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LclzUtils {

    public static void main(String[] args) throws Exception {
        //listTag("http://www.lclz.cn/index.php/vod/show/id/1.html");
        Tag tag = new Tag();
        tag.setUrl("http://www.lclz.cn/index.php/vod/show/id/6/page/page_param.html");
        listMovie(tag);
    }

    // http://www.lclz.cn/index.php/vod/show/id/1/page/1.html
    public static List<Tag> listTag(String url) throws Exception{
        List<Tag> tags = new ArrayList<>();
        Document result = Jsoup.parse(new URL(url),2000);
        Elements wrappers = result.getElementsByClass("wrapper_fl");
        for (Element wrapper:wrappers) {
            Elements lis = wrapper.getElementsByTag("li");
            Tag tag = new Tag();
            for (int i=0;i<lis.size();i++){

                if(i==0){
                    tag.setName(lis.get(i).text());
                }else if(i>=3) {
                    try{
                        Element a = lis.get(i).getElementsByTag("a").first();
                        if(StringUtils.isNotEmpty(a.attr("href"))){
                            Tag child = new Tag();
                            child.setName(a.text());
                            child.setUrl("http://www.lclz.cn/"+a.attr("href"));

                            dealUrl(child);

                            tag.getChildren().add(child);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            tags.add(tag);
        }

        return tags;

    }

    public static void listMovie(Tag tag) throws Exception {
        Integer page = 1;
        listMovie(tag,page);

    }


    public static List<Movie> listMovie(Tag tag,Integer page) throws Exception {
        List<Movie> movies = new ArrayList<>();
        String url = tag.getUrl().replace("page_param",""+page);
        Document document = Jsoup.parse(new URL(url),2000);
        Elements movieElements = document.getElementsByClass("vodlist_wi").first().getElementsByClass("vodlist_item");
        for (Element movieElement:movieElements) {
            Movie movie = new Movie();
            Element aElement = movieElement.getElementsByTag("a").first();
            String img = aElement.attr("data-original");
            String score = aElement.getElementsByClass("text_dy").first().text();
            String name = aElement.attr("title");
            String actors = movieElement.getElementsByClass("vodlist_sub").first().text();
            movie.setImg(img);
            movie.setTitle(name);
            movie.setActors(actors);
            movie.setScore(score);
            movie.getTags().add(tag.getName());
            movie.setLink("http://www.lclz.cn"+aElement.attr("href"));

            detail(movie);


            movies.add(movie);
        }
        return movies;
    }

    private static void detail(Movie movie) throws Exception{
        String link = movie.getLink();
        if(StringUtils.isNotEmpty(link)){
            Document document = Jsoup.parse(new URL(link),2000);
            Elements contentDetails = document.getElementsByClass("content_detail");
            Element contentDetail = contentDetails.get(1);
            Elements datas = contentDetail.getElementsByClass("data");
            Elements as = datas.get(0).getElementsByTag("a");
            // 长度为3
            as.get(0);//上映时间
            as.get(1);//地区
            as.get(2);//类型


            Elements spans = datas.get(1).getElementsByTag("span");
            spans.get(0);//状态：
            spans.get(1);//BD超清中字
            spans.get(2);//
            spans.get(3);//更新时间：
            System.out.println(datas.get(1).child(6).text());

        }
    }

    private static void dealUrl(Tag tag){
        String url = tag.getUrl();
        if(StringUtils.isNotEmpty(url)){
            url = url.replace(".html","")+"/page/page_param.html";
        }
        tag.setUrl(url);
        System.out.println(tag.getUrl());
    }






    // String url = "http://www.lclz.cn/play/29370_5_15.html";
    public static String playUrl(String url) throws Exception{
        Document result = Jsoup.parse(new URL(url),2000);
        Elements scripts = result.getElementsByTag("script");
        for (Element script:scripts) {
            String html = script.html();
            if(html.indexOf("player_data")>0){
                int position = html.indexOf("=");
                System.out.println(html.substring(position).substring(1));
            }
        }

        return "";
    }

}
