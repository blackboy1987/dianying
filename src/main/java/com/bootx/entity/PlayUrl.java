package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayUrl extends BaseEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String title;

    @Lob
    @Convert(converter = OptionConverter.class)
    @JsonView({BaseEntity.ViewView.class})
    private List<String> urls = new ArrayList<>();

    @NotNull
    @Column(nullable = false)
    private Boolean isEnabled;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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



