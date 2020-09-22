/**
 * Copyright 2020 bejson.com
 */
package com.bootx.entity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Auto-generated: 2020-09-21 19:51:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Entity
public class Movie extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private Long vod_id;
    private Integer type_id;
    private Integer type_id_1;
    private Integer group_id;
    private String vod_name;
    private String vod_sub;
    private String vod_en;
    private Integer vod_status;
    private String vod_letter;
    private String vod_color;
    private String vod_tag;
    private String vod_class;
    private String vod_pic;
    private String vod_pic_thumb;
    private String vod_pic_slide;
    private String vod_actor;
    private String vod_director;
    private String vod_writer;

    @Length(max = 4000)
    private String vod_behind;
    private String vod_blurb;
    private String vod_remarks;
    private String vod_pubdate;
    private Integer vod_total;
    private String vod_serial;
    private String vod_tv;
    private String vod_weekday;
    private String vod_area;
    private String vod_lang;
    private String vod_year;
    private String vod_version;
    private String vod_state;
    private String vod_author;
    private String vod_jumpurl;
    private String vod_tpl;
    private String vod_tpl_play;
    private String vod_tpl_down;
    private Integer vod_isend;
    private Integer vod_lock;
    private Integer vod_level;
    private Integer vod_copyright;
    private Integer vod_points;
    private Integer vod_points_play;
    private Integer vod_points_down;
    private Integer vod_hits;
    private Integer vod_hits_day;
    private Integer vod_hits_week;
    private Integer vod_hits_month;
    private String vod_duration;
    private Integer vod_up;
    private Integer vod_down;
    private String vod_score;
    private Integer vod_score_all;
    private Integer vod_score_num;
    private String vod_time;
    private Long vod_time_add;
    private Long vod_time_hits;
    private Integer vod_time_make;
    private Integer vod_trysee;
    private Integer vod_douban_id;
    private String vod_douban_score;
    private String vod_reurl;
    private String vod_rel_vod;
    private String vod_rel_art;
    private String vod_pwd;
    private String vod_pwd_url;
    private String vod_pwd_play;

    @Lob
    private String vod_pwd_play_url;
    private String vod_pwd_down;
    private String vod_pwd_down_url;

    @Lob
    private String vod_content;
    private String vod_play_from;
    private String vod_play_server;
    private String vod_play_note;
    @Lob
    private String vod_play_url;
    private String vod_down_from;
    private String vod_down_server;
    private String vod_down_note;
    private String vod_down_url;
    private Integer vod_plot;
    private String vod_plot_name;
    private String vod_plot_detail;
    private String type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieCategory movieCategory;

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy("order asc")
    private Set<MovieTag> movieTags = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<PlayUrl> playUrls = new HashSet<>();


    public void setVod_id(long vod_id) {
        this.vod_id = vod_id;
    }
    public long getVod_id() {
        return vod_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public int getType_id() {
        return type_id;
    }

    public void setType_id_1(int type_id_1) {
        this.type_id_1 = type_id_1;
    }
    public int getType_id_1() {
        return type_id_1;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
    public int getGroup_id() {
        return group_id;
    }

    public void setVod_name(String vod_name) {
        this.vod_name = vod_name;
    }
    public String getVod_name() {
        return vod_name;
    }

    public void setVod_sub(String vod_sub) {
        this.vod_sub = vod_sub;
    }
    public String getVod_sub() {
        return vod_sub;
    }

    public void setVod_en(String vod_en) {
        this.vod_en = vod_en;
    }
    public String getVod_en() {
        return vod_en;
    }

    public void setVod_status(int vod_status) {
        this.vod_status = vod_status;
    }
    public int getVod_status() {
        return vod_status;
    }

    public void setVod_letter(String vod_letter) {
        this.vod_letter = vod_letter;
    }
    public String getVod_letter() {
        return vod_letter;
    }

    public void setVod_color(String vod_color) {
        this.vod_color = vod_color;
    }
    public String getVod_color() {
        return vod_color;
    }

    public void setVod_tag(String vod_tag) {
        this.vod_tag = vod_tag;
    }
    public String getVod_tag() {
        return vod_tag;
    }

    public void setVod_class(String vod_class) {
        this.vod_class = vod_class;
    }
    public String getVod_class() {
        return vod_class;
    }

    public void setVod_pic(String vod_pic) {
        this.vod_pic = vod_pic;
    }
    public String getVod_pic() {
        return vod_pic;
    }

    public void setVod_pic_thumb(String vod_pic_thumb) {
        this.vod_pic_thumb = vod_pic_thumb;
    }
    public String getVod_pic_thumb() {
        return vod_pic_thumb;
    }

    public void setVod_pic_slide(String vod_pic_slide) {
        this.vod_pic_slide = vod_pic_slide;
    }
    public String getVod_pic_slide() {
        return vod_pic_slide;
    }

    public void setVod_actor(String vod_actor) {
        this.vod_actor = vod_actor;
    }
    public String getVod_actor() {
        return vod_actor;
    }

    public void setVod_director(String vod_director) {
        this.vod_director = vod_director;
    }
    public String getVod_director() {
        return vod_director;
    }

    public void setVod_writer(String vod_writer) {
        this.vod_writer = vod_writer;
    }
    public String getVod_writer() {
        return vod_writer;
    }

    public void setVod_behind(String vod_behind) {
        this.vod_behind = vod_behind;
    }
    public String getVod_behind() {
        return vod_behind;
    }

    public void setVod_blurb(String vod_blurb) {
        this.vod_blurb = vod_blurb;
    }
    public String getVod_blurb() {
        return vod_blurb;
    }

    public void setVod_remarks(String vod_remarks) {
        this.vod_remarks = vod_remarks;
    }
    public String getVod_remarks() {
        return vod_remarks;
    }

    public void setVod_pubdate(String vod_pubdate) {
        this.vod_pubdate = vod_pubdate;
    }
    public String getVod_pubdate() {
        return vod_pubdate;
    }

    public void setVod_total(int vod_total) {
        this.vod_total = vod_total;
    }
    public int getVod_total() {
        return vod_total;
    }

    public void setVod_serial(String vod_serial) {
        this.vod_serial = vod_serial;
    }
    public String getVod_serial() {
        return vod_serial;
    }

    public void setVod_tv(String vod_tv) {
        this.vod_tv = vod_tv;
    }
    public String getVod_tv() {
        return vod_tv;
    }

    public void setVod_weekday(String vod_weekday) {
        this.vod_weekday = vod_weekday;
    }
    public String getVod_weekday() {
        return vod_weekday;
    }

    public void setVod_area(String vod_area) {
        this.vod_area = vod_area;
    }
    public String getVod_area() {
        return vod_area;
    }

    public void setVod_lang(String vod_lang) {
        this.vod_lang = vod_lang;
    }
    public String getVod_lang() {
        return vod_lang;
    }

    public void setVod_year(String vod_year) {
        this.vod_year = vod_year;
    }
    public String getVod_year() {
        return vod_year;
    }

    public void setVod_version(String vod_version) {
        this.vod_version = vod_version;
    }
    public String getVod_version() {
        return vod_version;
    }

    public void setVod_state(String vod_state) {
        this.vod_state = vod_state;
    }
    public String getVod_state() {
        return vod_state;
    }

    public void setVod_author(String vod_author) {
        this.vod_author = vod_author;
    }
    public String getVod_author() {
        return vod_author;
    }

    public void setVod_jumpurl(String vod_jumpurl) {
        this.vod_jumpurl = vod_jumpurl;
    }
    public String getVod_jumpurl() {
        return vod_jumpurl;
    }

    public void setVod_tpl(String vod_tpl) {
        this.vod_tpl = vod_tpl;
    }
    public String getVod_tpl() {
        return vod_tpl;
    }

    public void setVod_tpl_play(String vod_tpl_play) {
        this.vod_tpl_play = vod_tpl_play;
    }
    public String getVod_tpl_play() {
        return vod_tpl_play;
    }

    public void setVod_tpl_down(String vod_tpl_down) {
        this.vod_tpl_down = vod_tpl_down;
    }
    public String getVod_tpl_down() {
        return vod_tpl_down;
    }

    public void setVod_isend(int vod_isend) {
        this.vod_isend = vod_isend;
    }
    public int getVod_isend() {
        return vod_isend;
    }

    public void setVod_lock(int vod_lock) {
        this.vod_lock = vod_lock;
    }
    public int getVod_lock() {
        return vod_lock;
    }

    public void setVod_level(int vod_level) {
        this.vod_level = vod_level;
    }
    public int getVod_level() {
        return vod_level;
    }

    public void setVod_copyright(int vod_copyright) {
        this.vod_copyright = vod_copyright;
    }
    public int getVod_copyright() {
        return vod_copyright;
    }

    public void setVod_points(int vod_points) {
        this.vod_points = vod_points;
    }
    public int getVod_points() {
        return vod_points;
    }

    public void setVod_points_play(int vod_points_play) {
        this.vod_points_play = vod_points_play;
    }
    public int getVod_points_play() {
        return vod_points_play;
    }

    public void setVod_points_down(int vod_points_down) {
        this.vod_points_down = vod_points_down;
    }
    public int getVod_points_down() {
        return vod_points_down;
    }

    public void setVod_hits(int vod_hits) {
        this.vod_hits = vod_hits;
    }
    public int getVod_hits() {
        return vod_hits;
    }

    public void setVod_hits_day(int vod_hits_day) {
        this.vod_hits_day = vod_hits_day;
    }
    public int getVod_hits_day() {
        return vod_hits_day;
    }

    public void setVod_hits_week(int vod_hits_week) {
        this.vod_hits_week = vod_hits_week;
    }
    public int getVod_hits_week() {
        return vod_hits_week;
    }

    public void setVod_hits_month(int vod_hits_month) {
        this.vod_hits_month = vod_hits_month;
    }
    public int getVod_hits_month() {
        return vod_hits_month;
    }

    public void setVod_duration(String vod_duration) {
        this.vod_duration = vod_duration;
    }
    public String getVod_duration() {
        return vod_duration;
    }

    public void setVod_up(int vod_up) {
        this.vod_up = vod_up;
    }
    public int getVod_up() {
        return vod_up;
    }

    public void setVod_down(int vod_down) {
        this.vod_down = vod_down;
    }
    public int getVod_down() {
        return vod_down;
    }

    public void setVod_score(String vod_score) {
        this.vod_score = vod_score;
    }
    public String getVod_score() {
        return vod_score;
    }

    public void setVod_score_all(int vod_score_all) {
        this.vod_score_all = vod_score_all;
    }
    public int getVod_score_all() {
        return vod_score_all;
    }

    public void setVod_score_num(int vod_score_num) {
        this.vod_score_num = vod_score_num;
    }
    public int getVod_score_num() {
        return vod_score_num;
    }

    public void setVod_time(String vod_time) {
        this.vod_time = vod_time;
    }
    public String getVod_time() {
        return vod_time;
    }

    public void setVod_time_add(long vod_time_add) {
        this.vod_time_add = vod_time_add;
    }
    public long getVod_time_add() {
        return vod_time_add;
    }

    public void setVod_time_hits(long vod_time_hits) {
        this.vod_time_hits = vod_time_hits;
    }
    public long getVod_time_hits() {
        return vod_time_hits;
    }

    public void setVod_time_make(int vod_time_make) {
        this.vod_time_make = vod_time_make;
    }
    public int getVod_time_make() {
        return vod_time_make;
    }

    public void setVod_trysee(int vod_trysee) {
        this.vod_trysee = vod_trysee;
    }
    public int getVod_trysee() {
        return vod_trysee;
    }

    public void setVod_douban_id(int vod_douban_id) {
        this.vod_douban_id = vod_douban_id;
    }
    public int getVod_douban_id() {
        return vod_douban_id;
    }

    public void setVod_douban_score(String vod_douban_score) {
        this.vod_douban_score = vod_douban_score;
    }
    public String getVod_douban_score() {
        return vod_douban_score;
    }

    public void setVod_reurl(String vod_reurl) {
        this.vod_reurl = vod_reurl;
    }
    public String getVod_reurl() {
        return vod_reurl;
    }

    public void setVod_rel_vod(String vod_rel_vod) {
        this.vod_rel_vod = vod_rel_vod;
    }
    public String getVod_rel_vod() {
        return vod_rel_vod;
    }

    public void setVod_rel_art(String vod_rel_art) {
        this.vod_rel_art = vod_rel_art;
    }
    public String getVod_rel_art() {
        return vod_rel_art;
    }

    public void setVod_pwd(String vod_pwd) {
        this.vod_pwd = vod_pwd;
    }
    public String getVod_pwd() {
        return vod_pwd;
    }

    public void setVod_pwd_url(String vod_pwd_url) {
        this.vod_pwd_url = vod_pwd_url;
    }
    public String getVod_pwd_url() {
        return vod_pwd_url;
    }

    public void setVod_pwd_play(String vod_pwd_play) {
        this.vod_pwd_play = vod_pwd_play;
    }
    public String getVod_pwd_play() {
        return vod_pwd_play;
    }

    public void setVod_pwd_play_url(String vod_pwd_play_url) {
        this.vod_pwd_play_url = vod_pwd_play_url;
    }
    public String getVod_pwd_play_url() {
        return vod_pwd_play_url;
    }

    public void setVod_pwd_down(String vod_pwd_down) {
        this.vod_pwd_down = vod_pwd_down;
    }
    public String getVod_pwd_down() {
        return vod_pwd_down;
    }

    public void setVod_pwd_down_url(String vod_pwd_down_url) {
        this.vod_pwd_down_url = vod_pwd_down_url;
    }
    public String getVod_pwd_down_url() {
        return vod_pwd_down_url;
    }

    public void setVod_content(String vod_content) {
        this.vod_content = vod_content;
    }
    public String getVod_content() {
        return vod_content;
    }

    public void setVod_play_from(String vod_play_from) {
        this.vod_play_from = vod_play_from;
    }
    public String getVod_play_from() {
        return vod_play_from;
    }

    public void setVod_play_server(String vod_play_server) {
        this.vod_play_server = vod_play_server;
    }
    public String getVod_play_server() {
        return vod_play_server;
    }

    public void setVod_play_note(String vod_play_note) {
        this.vod_play_note = vod_play_note;
    }
    public String getVod_play_note() {
        return vod_play_note;
    }

    public void setVod_play_url(String vod_play_url) {
        this.vod_play_url = vod_play_url;
    }
    public String getVod_play_url() {
        return vod_play_url;
    }

    public void setVod_down_from(String vod_down_from) {
        this.vod_down_from = vod_down_from;
    }
    public String getVod_down_from() {
        return vod_down_from;
    }

    public void setVod_down_server(String vod_down_server) {
        this.vod_down_server = vod_down_server;
    }
    public String getVod_down_server() {
        return vod_down_server;
    }

    public void setVod_down_note(String vod_down_note) {
        this.vod_down_note = vod_down_note;
    }
    public String getVod_down_note() {
        return vod_down_note;
    }

    public void setVod_down_url(String vod_down_url) {
        this.vod_down_url = vod_down_url;
    }
    public String getVod_down_url() {
        return vod_down_url;
    }

    public void setVod_plot(int vod_plot) {
        this.vod_plot = vod_plot;
    }
    public int getVod_plot() {
        return vod_plot;
    }

    public void setVod_plot_name(String vod_plot_name) {
        this.vod_plot_name = vod_plot_name;
    }
    public String getVod_plot_name() {
        return vod_plot_name;
    }

    public void setVod_plot_detail(String vod_plot_detail) {
        this.vod_plot_detail = vod_plot_detail;
    }
    public String getVod_plot_detail() {
        return vod_plot_detail;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    public String getType_name() {
        return type_name;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public void setVod_id(Long vod_id) {
        this.vod_id = vod_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public void setType_id_1(Integer type_id_1) {
        this.type_id_1 = type_id_1;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public void setVod_status(Integer vod_status) {
        this.vod_status = vod_status;
    }

    public void setVod_total(Integer vod_total) {
        this.vod_total = vod_total;
    }

    public void setVod_isend(Integer vod_isend) {
        this.vod_isend = vod_isend;
    }

    public void setVod_lock(Integer vod_lock) {
        this.vod_lock = vod_lock;
    }

    public void setVod_level(Integer vod_level) {
        this.vod_level = vod_level;
    }

    public void setVod_copyright(Integer vod_copyright) {
        this.vod_copyright = vod_copyright;
    }

    public void setVod_points(Integer vod_points) {
        this.vod_points = vod_points;
    }

    public void setVod_points_play(Integer vod_points_play) {
        this.vod_points_play = vod_points_play;
    }

    public void setVod_points_down(Integer vod_points_down) {
        this.vod_points_down = vod_points_down;
    }

    public void setVod_hits(Integer vod_hits) {
        this.vod_hits = vod_hits;
    }

    public void setVod_hits_day(Integer vod_hits_day) {
        this.vod_hits_day = vod_hits_day;
    }

    public void setVod_hits_week(Integer vod_hits_week) {
        this.vod_hits_week = vod_hits_week;
    }

    public void setVod_hits_month(Integer vod_hits_month) {
        this.vod_hits_month = vod_hits_month;
    }

    public void setVod_up(Integer vod_up) {
        this.vod_up = vod_up;
    }

    public void setVod_down(Integer vod_down) {
        this.vod_down = vod_down;
    }

    public void setVod_score_all(Integer vod_score_all) {
        this.vod_score_all = vod_score_all;
    }

    public void setVod_score_num(Integer vod_score_num) {
        this.vod_score_num = vod_score_num;
    }

    public void setVod_time_add(Long vod_time_add) {
        this.vod_time_add = vod_time_add;
    }

    public void setVod_time_hits(Long vod_time_hits) {
        this.vod_time_hits = vod_time_hits;
    }

    public void setVod_time_make(Integer vod_time_make) {
        this.vod_time_make = vod_time_make;
    }

    public void setVod_trysee(Integer vod_trysee) {
        this.vod_trysee = vod_trysee;
    }

    public void setVod_douban_id(Integer vod_douban_id) {
        this.vod_douban_id = vod_douban_id;
    }

    public void setVod_plot(Integer vod_plot) {
        this.vod_plot = vod_plot;
    }

    public Set<PlayUrl> getPlayUrls() {
        return playUrls;
    }

    public void setPlayUrls(Set<PlayUrl> playUrls) {
        this.playUrls = playUrls;
    }

    /**
     * 获取文章标签
     *
     * @return 文章标签
     */
    public Set<MovieTag> getMovieTags() {
        return movieTags;
    }

    /**
     * 设置文章标签
     *
     * @param movieTags
     *            文章标签
     */
    public void setMovieTags(Set<MovieTag> movieTags) {
        this.movieTags = movieTags;
    }
}