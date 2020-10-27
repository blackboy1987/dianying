/**
  * Copyright 2020 json.cn 
  */
package com.bootx.util.juHuiTV;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Auto-generated: 2020-10-24 22:11:45
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageProps {

    private List<NavGroup> navGroup;
    private MovieInfo movieInfo;
    private String keywords;
    private List<HotProgrammes> hotProgrammes;
    private List<MovieComments> movieComments;
    private List<VideoList> videoList;
    private int episode;
    private List<GuessLike> guessLike;
    private MinSharpnessResource minSharpnessResource;
    private List<DanmuList> danmuList;
    public void setNavGroup(List<NavGroup> navGroup) {
         this.navGroup = navGroup;
     }
     public List<NavGroup> getNavGroup() {
         return navGroup;
     }

    public void setMovieInfo(MovieInfo movieInfo) {
         this.movieInfo = movieInfo;
     }
     public MovieInfo getMovieInfo() {
         return movieInfo;
     }

    public void setKeywords(String keywords) {
         this.keywords = keywords;
     }
     public String getKeywords() {
         return keywords;
     }

    public void setHotProgrammes(List<HotProgrammes> hotProgrammes) {
         this.hotProgrammes = hotProgrammes;
     }
     public List<HotProgrammes> getHotProgrammes() {
         return hotProgrammes;
     }

    public void setMovieComments(List<MovieComments> movieComments) {
         this.movieComments = movieComments;
     }
     public List<MovieComments> getMovieComments() {
         return movieComments;
     }

    public void setVideoList(List<VideoList> videoList) {
         this.videoList = videoList;
     }
     public List<VideoList> getVideoList() {
         return videoList;
     }

    public void setEpisode(int episode) {
         this.episode = episode;
     }
     public int getEpisode() {
         return episode;
     }

    public void setGuessLike(List<GuessLike> guessLike) {
         this.guessLike = guessLike;
     }
     public List<GuessLike> getGuessLike() {
         return guessLike;
     }

    public void setMinSharpnessResource(MinSharpnessResource minSharpnessResource) {
         this.minSharpnessResource = minSharpnessResource;
     }
     public MinSharpnessResource getMinSharpnessResource() {
         return minSharpnessResource;
     }

    public void setDanmuList(List<DanmuList> danmuList) {
         this.danmuList = danmuList;
     }
     public List<DanmuList> getDanmuList() {
         return danmuList;
     }

}