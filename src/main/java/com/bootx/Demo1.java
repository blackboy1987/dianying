package com.bootx;

import com.bootx.util.JsonUtils;
import com.bootx.util.WebUtils;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {

    public static void main2(String[] args) {
        String s = WebUtils.get("https://api.okzy.tv/api.php/provide/vod/at/json/?ac=list", null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);


        System.out.println(jsonRootBean);
    }


    public static JsonRootBean category() {
        String s = WebUtils.get("https://api.okzy.tv/api.php/provide/vod/at/json/?ac=list", null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);
        return jsonRootBean;
    }

    public static List<Data> detail(Integer page) {
        String s = WebUtils.get("https://api.okzy.tv/api.php/provide/vod/at/json/?ac=detail&pg="+page, null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);
        List<Data> datas = jsonRootBean.getData();
        for (Data data:datas) {
            data.setVod_play_url(parseUrl(data.getVod_play_url()));
            data.setVod_down_url(parseUrl(data.getVod_down_url()));
        }
        return datas;
    }


    public static List<Data> detail(String ids) {
        String s = WebUtils.get("https://api.okzy.tv/api.php/provide/vod/at/json/?ac=detail&ids="+ids, null);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);
        List<Data> datas = jsonRootBean.getData();
        for (Data data:datas) {
            data.setVod_play_url(parseUrl(data.getVod_play_url()));
            data.setVod_down_url(parseUrl(data.getVod_down_url()));
        }
        return datas;
    }


    public static JsonRootBean search(String keywords,Integer page) {
        Map<String,Object> params = new HashMap<>();
        params.put("wd",keywords);
        params.put("pg",page);

        String s = WebUtils.get("https://api.okzy.tv/api.php/provide/vod/at/json/?ac=detail", params);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);
        List<Data> datas = jsonRootBean.getData();
        for (Data data:datas) {
            data.setVod_play_url(parseUrl(data.getVod_play_url()));
            data.setVod_down_url(parseUrl(data.getVod_down_url()));
        }
        return jsonRootBean;
    }



    // $$$ 源的区分。$:集数的区分
    private static String parseUrl(String vodPlayUrl) {
        String regEx="[$]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(vodPlayUrl);
        return m.replaceAll("@@@");

    }

    public static JsonRootBean list(int page) {
        String url ="https://api.okzy.tv/api.php/provide/vod/at/json/?ac=detail";
        Map<String,Object> params = new HashMap<>();
        params.put("h",24);
        params.put("pg",page);
        String s = WebUtils.get(url, params);
        JsonRootBean jsonRootBean = JsonUtils.toObject(s,JsonRootBean.class);
        return jsonRootBean;
    }

    public static void main(String[] args) {
        List<Data> detail = detail("62402");

        System.out.println(detail);
    }
}