
package com.bootx.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity - 地区
 *
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity
public class MovieCategory extends OrderedEntity<Long> {

    private static final long serialVersionUID = -2158109459123036967L;

    /**
     * 树路径分隔符
     */
    public static final String TREE_PATH_SEPARATOR = ",";

    /**
     * 名称
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false,updatable = false,unique = true)
    private String name;

    /**
     * 全称
     */
    @Column(nullable = false, length = 4000)
    private String fullName;

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
     * 上级地区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieCategory parent;

    /**
     * 下级地区
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("order asc")
    private Set<MovieCategory> children = new HashSet<>();

    /**
     * 会员
     */
    @ManyToMany(mappedBy = "movieCategories", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    private Long categoryId;


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

    /**
     * 获取全称
     *
     * @return 全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     *
     * @param fullName
     *            全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取树路径
     *
     * @return 树路径
     */
    public String getTreePath() {
        return treePath;
    }

    /**
     * 设置树路径
     *
     * @param treePath
     *            树路径
     */
    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    /**
     * 获取层级
     *
     * @return 层级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置层级
     *
     * @param grade
     *            层级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取上级地区
     *
     * @return 上级地区
     */
    public MovieCategory getParent() {
        return parent;
    }

    /**
     * 设置上级地区
     *
     * @param parent
     *            上级地区
     */
    public void setParent(MovieCategory parent) {
        this.parent = parent;
    }

    /**
     * 获取下级地区
     *
     * @return 下级地区
     */
    public Set<MovieCategory> getChildren() {
        return children;
    }

    /**
     * 设置下级地区
     *
     * @param children
     *            下级地区
     */
    public void setChildren(Set<MovieCategory> children) {
        this.children = children;
    }

    /**
     * 获取会员
     *
     * @return 会员
     */
    public Set<Movie> getMovies() {
        return movies;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 设置会员
     *
     * @param movies
     *            会员
     */
    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    /**
     * 获取所有上级地区ID
     *
     * @return 所有上级地区ID
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
     * 获取所有上级地区
     *
     * @return 所有上级地区
     */
    @Transient
    public List<MovieCategory> getParents() {
        List<MovieCategory> parents = new ArrayList<>();
        recursiveParents(parents, this);
        return parents;
    }

    /**
     * 递归上级地区
     *
     * @param parents
     *            上级地区
     * @param movieCategory
     *            地区
     */
    private void recursiveParents(List<MovieCategory> parents, MovieCategory movieCategory) {
        if (movieCategory == null) {
            return;
        }
        MovieCategory parent = movieCategory.getParent();
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
                movie.getMovieCategories().remove(this);
            }
        }
    }

}