package com.bootx.chengyu.entity;

import com.bootx.common.BaseAttributeConverter;
import com.bootx.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chengyu_idiom")
public class Idiom extends BaseEntity<Long> {

    @NotEmpty
    @Convert(converter = StringConvert.class)
    private List<String> text = new ArrayList<>();

    @NotEmpty
    @Convert(converter = StringConvert.class)
    private List<String> pinYin = new ArrayList<>();

    @NotEmpty
    @Convert(converter = StringConvert.class)
    private List<String> answers = new ArrayList<>();

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String fullName;

    @NotNull
    @Min(1)
    @Column(nullable = false,unique = true)
    private Integer level;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer position;


    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getPinYin() {
        return pinYin;
    }

    public void setPinYin(List<String> pinYin) {
        this.pinYin = pinYin;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


    @Convert
    public static class StringConvert extends BaseAttributeConverter<List<String>> {

    }

}
