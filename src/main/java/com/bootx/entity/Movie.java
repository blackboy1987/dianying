/**
 * Copyright 2020 bejson.com
 */
package com.bootx.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Auto-generated: 2020-09-21 19:51:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Entity
public class Movie extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,unique = true,updatable = false)
    private Long videoId;

    private String title;

    private String img;
}