package com.bootx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo2 {

    public static void main(String[] args) {
        String playFrom = "kuyun$$$ckm3u8";
        String regEx="[$]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(playFrom);
        String url = m.replaceAll("@");
        System.out.println(url);
    }

}