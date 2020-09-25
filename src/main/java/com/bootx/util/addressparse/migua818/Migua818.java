package com.bootx.util.addressparse.migua818;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析：http://www.migua818.com/
 */
public class Migua818 {

    public static final String movie = "http://www.migua818.com/movie.html";

    // 最后一个参数是第几页
    public static final String url = "http://www.migua818.com/movie/0-5-2015-1-8.html";


    public static List<Tag> index() throws IOException {
        List<Tag> rootTags = new ArrayList<>();
        Document document = Jsoup.parse(new URL(movie),5000);
        Elements rootElements = document.getElementsByClass("stui-screen__list");

        for (Element rootElement:rootElements) {
            Tag rootTag = new Tag();
            Elements lis = rootElement.getElementsByTag("li");
            for (int i=0;i<lis.size();i++) {
                if(i==0){
                    rootTag.setName(lis.get(0).text());
                }else{
                    Tag child = new Tag();
                    Element a = lis.get(i).getElementsByTag("a").first();
                    child.setName(a.text());
                    child.setUrl(a.attr("href"));
                    rootTag.getChildren().add(child);
                }
            }
            rootTags.add(rootTag);
        }
        return rootTags;
    }

    public static void list(String url) throws Exception{
        Document document = Jsoup.parse(new URL(url),5000);
        Elements rootElements = document.getElementsByClass("stui-pannel_bd");
        if(rootElements.size()==2){
            // 存在数据
            Element videoListElement = rootElements.get(1);
            Element videoUlElement = videoListElement.getElementsByTag("ul").first();
            Elements items = videoUlElement.getElementsByTag("li");

            for (Element item:items) {
                Movie movie = new Movie();
                Element a = item.getElementsByTag("a").first();
                String link = a.attr("href");
                movie.setLink(link);
                detail(movie);
            }
        }
    }

    private static Movie detail(Movie movie) throws Exception {
        Document document = Jsoup.parse(new URL(movie.getLink()),5000);

        // 封面信息
        Element first = document.getElementsByClass("stui-content__thumb").first();
        String img = first.getElementsByTag("img").first().attr("src");
        Element detail = document.getElementsByClass("stui-content__detail").first();
        String title = detail.getElementsByTag("h1").first().text();
        String playUrl = detail.getElementsByTag("h1").first().getElementsByTag("a").first().attr("href");
        String jianjie = document.getElementsByClass("detail-sketch").first().text();
        movie.setImg(img);
        movie.setTitle(title);
        movie.setJianjie(jianjie);
        movie.setPlayUrl(playUrl);

        // 多少个播放地址（可能会是数组）
        Elements elementsByClass = document.getElementsByClass("stui-content__playlist");
        for (Element element:elementsByClass) {
            PlayInfo playInfo = new PlayInfo();
            playInfo.setTitle("播放地址");
            Elements lis = element.getElementsByTag("li");
            for (int i=0;i<lis.size();i++){
                PlayUrl playUrl1 = new PlayUrl();
                Element a = lis.get(i).getElementsByTag("a").first();
                playUrl1.setName(a.text());
                playUrl1.setUrl(a.attr("href"));
                playUrl1.setOrder(i+1);
                playInfo.getPlayUrls().add(playUrl1);
                playInfoRealUrl(playUrl1);
            }
            movie.getPlayInfos().add(playInfo);
        }
        Elements p = detail.getElementsByTag("p");
        for (Element tag1:p) {
            movie.getTags().add(tag1.text());
        }

        return movie;
    }

    private static PlayUrl playInfoRealUrl(PlayUrl playUrl1) throws IOException {

        Document doc = Jsoup.parse(new URL(playUrl1.getUrl()),20000);

        String text = doc.html();

        String[] iframes = text.split("iframe");
        Document iframe = Jsoup.parse("<iframe"+iframes[1]+"></iframe>");
        playUrl1.setUrl1(iframe.getElementsByTag("iframe").first().attr("src"));
        Document parse = Jsoup.parse(new URL(playUrl1.getUrl1()), 2000);


        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10 * 1000);
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(3 * 1000);
        String pageAsXml = page.asXml();



        Document doc1 = Jsoup.parse(pageAsXml, playUrl1.getUrl());
        String attr = parse.getElementsByTag("video").first().attr("src");
        playUrl1.setUrl1(attr);

        return playUrl1;
    }


    public static void main(String[] args) throws Exception {
        PlayUrl playUrl = new PlayUrl();
        playUrl.setUrl("http://www.migua818.com/tvdetial/129478-914259.html");
        playInfoRealUrl(playUrl);

    }


    private static String encode(String _str){
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        String staticchars = "PXhw7UT1B0a9kQDKZsjIASmOezxYG4CHo5Jyfg2b8FLpEvRr3WtVnlqMidu6cN";
        String encodechars = "";
        for(int i=0;i<_str.length();i++){
            int num0 = staticchars.indexOf(_str.toCharArray()[i]);
            char code;
            if(num0 == -1){
                code = _str.toCharArray()[i];
            }else{
                code = staticchars.toCharArray()[(num0+3)%62];
            }
            int num1 = Integer.valueOf(df.format(Math.random()*62));
            int num2 = Integer.valueOf(df.format(Math.random()*62));
            encodechars += staticchars.toCharArray()[num1]+code+staticchars.toCharArray()[num2];
        }
        return encodechars;
    }
}
