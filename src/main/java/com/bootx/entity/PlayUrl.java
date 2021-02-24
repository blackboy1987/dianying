package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayUrl extends BaseEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Movie1 movie1;

    @JsonView({ViewView.class})
    private String title;

    @Lob
    @Convert(converter = OptionConverter.class)
    @JsonView({BaseEntity.ViewView.class})
    private List<String> urls = new ArrayList<>();

    @NotNull
    @Column(nullable = false)
    private Boolean isEnabled;

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



