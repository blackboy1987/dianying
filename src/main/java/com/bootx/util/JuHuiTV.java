package com.bootx.util;

import com.bootx.entity.Movie;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.util.juHuiTV.JsonRootBean;
import com.bootx.util.juHuiTV.MovieInfo;
import com.bootx.util.juHuiTV.Resources;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public final class JuHuiTV {


    public static void main(String[] args) throws Exception {
        // search();
        parseDetail1("https://www.juhuitv.com/moviedetail/5f47098c187856003aae520f.html");
    }

    public static List<Movie> search(Integer page) throws Exception {
        List<Movie> movies = new ArrayList<>();
        Document document = Jsoup.parse(new URL("https://www.juhuitv.com/search/''/"+page+"/new/all/all/all/all.html"), 5000);
        Elements elementsByTags = document.getElementsByClass("lists___3_5Ll").first().getElementsByTag("li");
        try{
            for (Element li:elementsByTags) {
                Element a = li.getElementsByTag("a").first();
                Movie movie = parseDetail1(a.attr("href").replaceAll("watchmovie", "moviedetail").replaceAll("/1.html", ".html"));
                movies.add(movie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return movies;
    }


    private static void search() throws Exception {
        for (int i = 1; i <=1; i++) {
            Document document = Jsoup.parse(new URL("https://www.juhuitv.com/search/''/"+i+"/new/all/all/all/all.html"), 5000);
            Elements elementsByTags = document.getElementsByClass("lists___3_5Ll").first().getElementsByTag("li");

            for (Element li:elementsByTags) {
                Element a = li.getElementsByTag("a").first();
                Movie movie = parseDetail1(a.attr("href").replaceAll("watchmovie", "moviedetail").replaceAll("/1.html", ".html"));
            }
        }
    }

    private static Movie parseDetail1(String url) throws Exception {
        Movie movie = new Movie();

        Document document = Jsoup.parse(new URL(url), 5000);
        List<Element> collect = document.getElementsByTag("script").stream().filter(script -> StringUtils.equals(script.attr("id"), "__NEXT_DATA__")).collect(Collectors.toList());
        JsonRootBean jsonRootBean = JsonUtils.toObject(collect.get(0).data(), JsonRootBean.class);
        MovieInfo movieInfo = jsonRootBean.getProps().getPageProps().getMovieInfo();

        System.out.println(movieInfo);


        movie.setArea(movieInfo.getLocation());
        movie.setVideoId("juHuiTv_"+movieInfo.getId());
        movie.setIsShow(true);
        movie.setSerial(movieInfo.getEpisodesCount()+"");
        movie.setImg(movieInfo.getVerticalCover());
        movie.setTitle(movieInfo.getTitle());
        movie.setBlurb(movieInfo.getSummary());
        movie.setContent(movieInfo.getSummary());
        movie.setScore(0d);
        movie.setStatus(movieInfo.getUpdateState());
        movie.setUpdateTime(new Date(movieInfo.getUpdatedAt()));
        List<String> actors = movieInfo.getActors().stream().map(actor -> actor.getName()).collect(Collectors.toList());
        movie.setActors(StringUtils.join(actors,","));
        String remarks = "更新至"+movieInfo.getResourceCount()+"集";
        if(movieInfo.getIsSerial()){
            if(movieInfo.getResourceCount()==movieInfo.getEpisodesCount()){
                remarks = "完结";
            }
        }else{
            remarks="HD";
        }
        movie.setRemarks(remarks);
        movie.setPlayUrls(getPlayUrls(movieInfo,movie));
        MovieCategory movieCategory = new MovieCategory();
        movieCategory.setIsShow(true);
        movieCategory.setName(movieInfo.getCategory().getName());
        movieCategory.setOtherId(movieInfo.getCategory().getId());
        movie.setMovieCategory(movieCategory);

        return movie;

    }


    private static void parseDetail(String url) throws Exception {
        Movie movie = new Movie();

        Document document = Jsoup.parse(new URL(url), 5000);
        Element movieInfoContainer = document.getElementsByClass("movieInfoContainer___1JP6G").first();
        Element movieInfo = movieInfoContainer.getElementsByClass("movieInfo___1RUZd").first();
        Element imgFirst = movieInfo.getElementsByTag("img").first();
        Element h2First = movieInfo.getElementsByTag("h2").first();
        // 封面图片
        String img = imgFirst.attr("src");
        // 名称
        String title = imgFirst.attr("alt");
        Element h2FirstNext = h2First.nextElementSibling();
        String[] h2FirstNextText = h2FirstNext.text().trim().split("/");
        String h2FirstNextText0 = h2FirstNextText[0];
        String h2FirstNextText1 = h2FirstNextText[1].replaceAll("集","");
        String remarks = "更新至"+h2FirstNextText[0]+"集";
        if(StringUtils.equals(h2FirstNextText1,h2FirstNextText0)){
            remarks = "完结";
        }
        Element actorsElement = h2FirstNext.nextElementSibling();
        Elements spans = actorsElement.getElementsByTag("span");
        List<String> actors = new ArrayList<>();
        for (Element span:spans) {
            if(StringUtils.isNoneBlank(span.text())){
                actors.add(span.text());
            }
        }

        Element leiXingElement = actorsElement.nextElementSibling();
        String leiXing = leiXingElement.text().replace("类型：","");

        Element dateElement = leiXingElement.nextElementSibling();
        String date = dateElement.text().replace("时间：","");

        Element contentElement = dateElement.nextElementSibling();
        String content = dateElement.text().replace("简介： ","");

        movie.setImg(img);
        movie.setTitle(title);
        movie.setRemarks(remarks);
        movie.setSerial(h2FirstNextText1);
        movie.setActors(StringUtils.join(actors,","));
        movie.setYear(date);
        movie.setContent(content);
        movie.setBlurb(content);





        // playUrls
        Set<PlayUrl> playUrls = getPlayUrls(contentElement.nextElementSibling(),movie);
        movie.setPlayUrls(playUrls);
        System.out.println(movie);
    }

    private static Set<PlayUrl> getPlayUrls(Element playUrlElement,Movie movie) throws Exception {
        Set<PlayUrl> playUrls = new HashSet<>();
        PlayUrl playUrl = new PlayUrl();
        playUrl.setTitle("线路1");
        List<String> urls = new ArrayList<>();
        playUrl.setMovie(movie);
        Elements aElements = playUrlElement.getElementsByTag("a");
        for(Element a:aElements){
            String text = "第"+a.text()+"集";
            String url = getVideoUrl(text,a.attr("href"));
            urls.add(text+"@@@"+url);
        }
        playUrl.setIsEnabled(true);
        playUrl.setUrls(urls);
        playUrls.add(playUrl);
        return playUrls;
    }

    private static Set<PlayUrl> getPlayUrls(MovieInfo movieInfo,Movie movie){
        Set<PlayUrl> playUrls = new HashSet<>();
        PlayUrl playUrl = new PlayUrl();
        playUrl.setTitle("线路1");
        List<String> urls = new ArrayList<>();
        playUrl.setMovie(movie);
        for(Resources resources:movieInfo.getResources()){
            String text = "第"+resources.getEpisode()+"集";
            if(!movieInfo.getIsSerial()){
                text = "HD";
            }
            String url = "https://ipfs69.365kqzs.cn:9081/ipfs/"+resources.getData().stream().filter(data -> data.getDisplayName().equals("标准")).collect(Collectors.toList()).get(0).getHash();
            urls.add(text+"@@@"+url);
        }
        playUrl.setIsEnabled(true);
        playUrl.setUrls(urls);
        playUrls.add(playUrl);
        return playUrls;
    }

    private static String getVideoUrl(String text,String href){
        try{
            Document document = Jsoup.parse(new URL(href), 5000);
            List<Element> collect = document.getElementsByTag("script").stream().filter(script -> StringUtils.equals(script.attr("id"), "__NEXT_DATA__")).collect(Collectors.toList());
            JsonRootBean jsonRootBean = JsonUtils.toObject(collect.get(0).data(), JsonRootBean.class);
            String hash = jsonRootBean.getProps().getPageProps().getMinSharpnessResource().getHash();
            System.out.println(text+":"+hash);
            return "https://ipfs69.365kqzs.cn:9081/ipfs/"+hash;
        }catch (Exception e){
            return "";
        }
    }


}
