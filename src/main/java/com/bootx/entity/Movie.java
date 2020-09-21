/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.bootx.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity - 地区
 *
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity
public class Movie extends BaseEntity<Long> {

    /**
     * 名称
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    private String name;

    private String pic;

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy("order asc")
    private Set<com.igomall.entity.MovieCategory> movieCategories = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<com.igomall.entity.MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(Set<com.igomall.entity.MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }
}