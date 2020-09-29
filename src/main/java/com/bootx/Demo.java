package com.bootx;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.Data;
import com.bootx.vo.JsonRootBean;
import com.bootx.vo.detail.JsonRootDetailBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

    public static void main1(String[] args) {
        String content = "IEC3OARSJZAB4SG3TU";
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4");

        String encryptHex = sm4.encryptHex(content);
        System.out.println(encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);//test中文
        System.out.println(decryptStr);
    }

    public static void main2(String[] args) throws IOException {
        String url = "http://www.lclz.cn/index.php/vod/show/id/1.html";
        Document document = Jsoup.parse(new URL(url),2000);
        Elements wrapper_fls = document.getElementsByClass("screen_list");
        StringBuffer sb = new StringBuffer();


        for (int i=0;i<wrapper_fls.size()-1;i++){
            Elements lis = wrapper_fls.get(i).getElementsByTag("li");
            String rootName = "";
            for (int j = 0; j <lis.size(); j++) {
                if(j==0){
                    rootName = lis.get(j).text();
                    sb.append(rootName+"::");
                    System.out.print(rootName+":");
                }else if(j>=3){
                    System.out.print(lis.get(j).text());
                    sb.append(lis.get(j).text()+":");
                }
            }
            sb.append(";");
        }
        System.out.println(sb.toString());
    }


    public static void main3(String[] args) throws IOException {
        String str = "分类::动作片:喜剧片:爱情片:科幻片:恐怖片:剧情片:战争片:纪录片:动画片:惊悚片:预告片:犯罪片:伦理片:;类型::喜剧:爱情:恐怖:动作:科幻:剧情:战争:警匪:犯罪:动画:奇幻:武侠:冒险:枪战:恐怖:悬疑:惊悚:经典:青春:文艺:微电影:古装:历史:运动:农村:儿童:网络电影:;地区::大陆:香港:台湾:美国:法国:英国:日本:韩国:德国:泰国:印度:意大利:西班牙:加拿大:其他:;年份::2020:2019:2018:2017:2016:2015:2014:2013:2012:2011:2010:;语言::国语:英语:粤语:闽南语:韩语:日语:法语:德语:其它:;字母::A:B:C:D:E:F:G:H:I:J:K:L:M:N:O:P:Q:R:S:T:U:V:W:X:Y:Z:0-9:;";
        String [] str1s = str.split(";");
        System.out.println(str1s.length);
        for (String str1:str1s) {
            // 分类:动作片|喜剧片|爱情片|科幻片|恐怖片|剧情片|战争片|纪录片|动画片|惊悚片|预告片|犯罪片|伦理片|
            String root = str1.split("::")[0];
            String children = str1.split("::")[1];
            String[] childs = children.split(":");


            System.out.println(childs.length);


        }
    }

    public static void main(String[] args) {
        Data data = main4("181088");
        System.out.println(data.getVod_play_url());
    }


    public static List<Data> main5(Integer type,Integer page){
        String baseUrl="https://www.i-gomall.com/app/index.php?i=2&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideoList&m=sg_movie&sign=1027208b869c87eda171c4a80706b426";

        Map<String,Object> params = new HashMap<>();
        params.put("t",type);
        params.put("page",page);
        String result = WebUtils.get(baseUrl,params);
        JsonRootBean jsonRootDetailBean = JsonUtils.toObject(result,JsonRootBean.class);
        List<Data> datas = jsonRootDetailBean.getData();
        for (Data data:datas) {
            data.setVod_play_url(parseUrl(data.getVod_play_url()));
        }
        return datas;
    }

    public static Data main4(String id){
        System.out.println("main:"+id);
        String url = "https://bg.zqbkk.cn/app/index.php?i=2&t=0&v=1.0&from=wxapp&c=entry&a=wxapp&do=GetVideo&m=sg_movie&sign=0dbccf54fe0919ef7bcde945407ceb13";
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        String result = WebUtils.get(url,params);
        JsonRootDetailBean jsonRootDetailBean = JsonUtils.toObject(result,JsonRootDetailBean.class);
        Data data = jsonRootDetailBean.getData();
        if(data!=null){
            data.setVod_play_url(parseUrl(data.getVod_play_url()));
        }
        return data;
    }

    // $$$ 源的区分。$:集数的区分
    private static String parseUrl(String vodPlayUrl) {
        String regEx="[$]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(vodPlayUrl);
        return m.replaceAll(":");

    }

}