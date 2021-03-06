package com.bootx.app.tuzhuanwen;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Serializable {

    @JsonProperty("words_block_count")
    private Integer wordsBlockCount;

    @JsonProperty("words_block_list")
    @Convert(converter = WordsBlockListListConverter.class)
    private List<WordsBlockList> wordsBlockListList = new ArrayList();


    public Integer getWordsBlockCount() {
        return wordsBlockCount;
    }

    public void setWordsBlockCount(Integer wordsBlockCount) {
        this.wordsBlockCount = wordsBlockCount;
    }

    public List<WordsBlockList> getWordsBlockListList() {
        return wordsBlockListList;
    }

    public void setWordsBlockListList(List<WordsBlockList> wordsBlockListList) {
        this.wordsBlockListList = wordsBlockListList;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WordsBlockList implements Serializable{

        private String words;

        private Double confidence;

        @JsonProperty("location")
        @Convert(converter = LocationsConverter.class)
        private List<List<Integer>> locations = new ArrayList<>();

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }

        public List<List<Integer>> getLocations() {
            return locations;
        }

        public void setLocations(List<List<Integer>> locations) {
            this.locations = locations;
        }
    }











    @Converter
    public static class WordsBlockListListConverter extends BaseAttributeConverter<List<WordsBlockList>> {
    }

    @Converter
    public static class LocationsConverter extends BaseAttributeConverter<List<List<Integer>>> {
    }



}
