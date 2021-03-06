package com.bootx.app.chengyu.entity;

import com.bootx.common.BaseAttributeConverter;
import com.bootx.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "chengyu_idiom_level")
public class IdiomLevel extends BaseEntity<Long> {

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String word;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer position;

    @NotNull
    @Min(1)
    @Column(nullable = false,unique = true)
    private Integer level;


    @Convert(converter = AnswersConvert.class)
    private List<String> answers;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Convert
    public static class AnswersConvert extends BaseAttributeConverter<List<String>> {

    }
}
