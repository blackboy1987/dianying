package com.bootx.es;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 商品
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Document(indexName="movie")
public class EsMovie implements Serializable {

	private static final long serialVersionUID = -6977025562650112419L;
	public static final String ES_NAME = "movie";

	@Id
	private Long id;

	private String title;

	private String english;

	private String remarks;

	private Long movieCategoryId;

	private String movieCategoryName;

	private Long movieCategoryParentId;

	private String movieCategoryParentName;

	private String movieCategoryTreePath;

	private String sub;

	private String pic;

	private String actor;

	private String director;

	private String lang;

	private String area;

	/**
	 * 更新时间
	 */
	private Date time;

	/**
	 * 添加时间
	 */
	private Date addTime;

	private String content;

	private String year;


	private String playUrls;

	private String downloadUrls;

	private Double score;

	private Boolean isShow;

	private Date updateTime;

	/**
	 * 简介
	 */
	private String blurb;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getMovieCategoryId() {
		return movieCategoryId;
	}

	public void setMovieCategoryId(Long movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}

	public String getMovieCategoryName() {
		return movieCategoryName;
	}

	public void setMovieCategoryName(String movieCategoryName) {
		this.movieCategoryName = movieCategoryName;
	}

	public Long getMovieCategoryParentId() {
		return movieCategoryParentId;
	}

	public void setMovieCategoryParentId(Long movieCategoryParentId) {
		this.movieCategoryParentId = movieCategoryParentId;
	}

	public String getMovieCategoryParentName() {
		return movieCategoryParentName;
	}

	public void setMovieCategoryParentName(String movieCategoryParentName) {
		this.movieCategoryParentName = movieCategoryParentName;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getMovieCategoryTreePath() {
		return movieCategoryTreePath;
	}

	public void setMovieCategoryTreePath(String movieCategoryTreePath) {
		this.movieCategoryTreePath = movieCategoryTreePath;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPlayUrls() {
		return playUrls;
	}

	public void setPlayUrls(String playUrls) {
		this.playUrls = playUrls;
	}

	public String getDownloadUrls() {
		return downloadUrls;
	}

	public void setDownloadUrls(String downloadUrls) {
		this.downloadUrls = downloadUrls;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
}