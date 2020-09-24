
package com.bootx.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity - 文章标签
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity
public class MovieTag extends OrderedEntity<Long> {

	private static final long serialVersionUID = -2735037966597250149L;

	/**
	 * 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	private String name;

	/**
	 * 图标
	 */
	@Length(max = 200)
	@Pattern(regexp = "^(?i)(http:\\/\\/|https:\\/\\/|\\/).*$")
	private String icon;

	/**
	 * 备注
	 */
	@Length(max = 200)
	private String memo;

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

	/**
	 * 获取图标
	 * 
	 * @return 图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置图标
	 * 
	 * @param icon
	 *            图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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