package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zhaocha_level")
public class Level extends BaseEntity<Long> {

    @Valid
    @Column(length = 4000)
    @Convert(converter = LevelImageConverter.class)
    private List<LevelImage> content = new ArrayList<>();

    private Long rank;

    private Long rankUp;

    public List<LevelImage> getContent() {
        return content;
    }

    public void setContent(List<LevelImage> content) {
        this.content = content;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getRankUp() {
        return rankUp;
    }

    public void setRankUp(Long rankUp) {
        this.rankUp = rankUp;
    }

    @Converter
    public static class LevelImageConverter extends BaseAttributeConverter<List<LevelImage>> {
    }

}
