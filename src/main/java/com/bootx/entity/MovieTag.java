/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: ckxHhXqFRhNK8nBuDQbPIASDGEBLBWQ2
 */
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity - 文章标签
 * 
 * @author bootx Team
 * @version 6.1
 */
@Entity
public class MovieTag extends OrderedEntity<Long> {

	private static final long serialVersionUID = -2735037966597250149L;
	/**
	 * 树路径分隔符
	 */
	public static final String TREE_PATH_SEPARATOR = ",";
	/**
	 * 名称
	 */
	@JsonView(BaseView.class)
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	private String name;

	/**
	 * 树路径
	 */
	@Column(nullable = false)
	private String treePath;

	/**
	 * 层级
	 */
	@Column(nullable = false)
	private Integer grade;

	/**
	 * 上级分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private MovieTag parent;

	/**
	 * 下级分类
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	private Set<MovieTag> children = new HashSet<>();

	/**
	 * 文章
	 */
	@ManyToMany(mappedBy = "movieTags", fetch = FetchType.LAZY)
	private Set<Movie> movies = new HashSet<>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public MovieTag getParent() {
		return parent;
	}

	public void setParent(MovieTag parent) {
		this.parent = parent;
	}

	public Set<MovieTag> getChildren() {
		return children;
	}

	public void setChildren(Set<MovieTag> children) {
		this.children = children;
	}

	/**
	 * 获取文章
	 * 
	 * @return 文章
	 */
	public Set<Movie> getMovies() {
		return movies;
	}

	/**
	 * 设置文章
	 * 
	 * @param movies
	 *            文章
	 */
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	/**
	 * 获取所有上级分类ID
	 *
	 * @return 所有上级分类ID
	 */
	@Transient
	public Long[] getParentIds() {
		String[] parentIds = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		Long[] result = new Long[parentIds.length];
		for (int i = 0; i < parentIds.length; i++) {
			result[i] = Long.valueOf(parentIds[i]);
		}
		return result;
	}

	/**
	 * 获取所有上级分类
	 *
	 * @return 所有上级分类
	 */
	@Transient
	public List<MovieTag> getParents() {
		List<MovieTag> parents = new ArrayList<>();
		recursiveParents(parents, this);
		return parents;
	}

	/**
	 * 递归上级分类
	 *
	 * @param parents
	 *            上级分类
	 * @param movieTag
	 *            文章分类
	 */
	private void recursiveParents(List<MovieTag> parents, MovieTag movieTag) {
		if (movieTag == null) {
			return;
		}
		MovieTag parent = movieTag.getParent();
		if (parent != null) {
			parents.add(0, parent);
			recursiveParents(parents, parent);
		}
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		Set<Movie> movies = getMovies();
		if (movies != null) {
			for (Movie movie : movies) {
				movie.getMovieTags().remove(this);
			}
		}
	}



}