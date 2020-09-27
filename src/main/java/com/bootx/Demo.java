package com.bootx;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("第05集$http://youku.com-youku.net/20180615/12116_f7c41a8c/index.m3u8");
        list.add("第10集$http://youku.com-youku.net/20180615/12121_61d87b21/index.m3u8");
        list.add("第06集$http://youku.com-youku.net/20180615/12117_d958245d/index.m3u8");
        list.add("第07集$http://youku.com-youku.net/20180615/12118_5682adc1/index.m3u8");
        list.add("第02集$http://youku.com-youku.net/20180615/12113_06bf8b6c/index.m3u8");
        list.add("第04集$http://youku.com-youku.net/20180615/12115_f6902770/index.m3u8");
        list.add("第08集$http://youku.com-youku.net/20180615/12119_0e4f93b3/index.m3u8");
        list.add("第01集$http://youku.com-youku.net/20180615/12112_ff361b12/index.m3u8");
        list.add("第03集$http://youku.com-youku.net/20180615/12114_04cd6462/index.m3u8");
        list.add("第09集$http://youku.com-youku.net/20180615/12120_2c047a84/index.m3u8");
        System.out.println(list);
        list.sort((i,j)->i.compareTo(j));
        System.out.println(list);


    }
}