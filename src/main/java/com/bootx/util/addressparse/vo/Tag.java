package com.bootx.util.addressparse.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Tag implements Serializable {

    private String name;

    private Set<Tag> children = new HashSet<>();

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tag> getChildren() {
        return children;
    }

    public void setChildren(Set<Tag> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
