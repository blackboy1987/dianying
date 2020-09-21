package com.bootx.entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayUrl implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String title;

    private List<Url> urls = new ArrayList<>();

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

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public static class Url implements Serializable, Comparable<Url>  {

        private Integer index;

        private String url;


        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int compareTo(Url o) {
            return this.getIndex()-o.index;
        }
    }
}



