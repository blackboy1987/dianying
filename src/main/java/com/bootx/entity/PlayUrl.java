package com.bootx.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PlayUrl extends BaseEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String title;

    @OneToMany(mappedBy = "playUrl",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OrderBy("order asc ")
    private Set<Url> urls = new HashSet<>();

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

    public Set<Url> getUrls() {
        return urls;
    }

    public void setUrls(Set<Url> urls) {
        this.urls = urls;
    }
}



