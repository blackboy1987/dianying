package com.bootx.entity;

import javax.persistence.*;

public class MovieDetail extends BaseEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private Movie1 movie;

    private Integer status;

    private String letter;


    private String thumb;

    private String picSlide;



    private String writer;

    private String behind;

    private String blurb;

    private String pubDate;

    private Long total;

    private String serial;

    private String tv;

    private String weekday;



    private String version;

    private String state;

    private String author;

    private String jumpUrl;

    private String tpl;

    private String tplPlay;

    private String tplDown;

    private Integer sSend;

    private Integer lock;

    private Integer level;

    private Integer copyRight;

    private Integer points;

    private Integer pointsPlay;

    private Integer pointsDown;

    private Long hits;

    private Long hitsDay;

    private Long hitsWeek;

    private Long hitsMonth;

    private String duration;

    private Long up;

    private Long down;

    private String score;

    private Long scoreNum;

    private String scoreAll;


    private Long timeHits;

    private Long timeMake;

    private Long trySee;

    private Long douBanId;

    private String douBanScore;

    private String reUrl;

    private String pwd;

    private String pwdUrl;

    private String pwdPlay;

    private String pwdPlayUrl;

    private String pwdDown;

    private String pwdDownUrl;



    private String playServer;

    private String playNote;

    private String playUrl;

    private String downFrom;

    private String downServer;

    private String downNote;

    private String downUrl;
    private String typeName;




















}
