package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DownloadUrl extends BaseEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie1 movie1;

    private String title;

    @Lob
    @Convert(converter = OptionConverter.class)
    @JsonView({ViewView.class})
    private List<String> urls = new ArrayList<>();

    public Movie1 getMovie1() {
        return movie1;
    }

    public void setMovie1(Movie1 movie1) {
        this.movie1 = movie1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    /**
     * 类型转换 - 可选项
     *
     * @author bootx Team
     * @version 6.1
     */
    @Converter
    public static class OptionConverter extends BaseAttributeConverter<List<String>> {
    }
}



