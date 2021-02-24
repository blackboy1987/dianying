
package com.bootx.service.impl;

import com.bootx.Demo1;
import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.Movie1Dao;
import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.es.service.EsMovieService;
import com.bootx.service.Movie1Service;
import com.bootx.service.MovieCategoryService;
import com.bootx.util.DateUtils;
import com.bootx.vo.okzy.com.jsoncn.pojo.Data;
import com.bootx.vo.okzy.com.jsoncn.pojo.JsonRootBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class Movie1ServiceImpl extends BaseServiceImpl<Movie1, Long> implements Movie1Service {

    @Autowired
    private Movie1Dao movie1Dao;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Resource
    private EsMovieService esMovieService;
    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public Movie1 save(Movie1 movie1){
        movie1 = super.save(movie1);
        esMovieService.add(movie1);
        return movie1;
    }

    @Override
    public Movie1 update(Movie1 movie1) {
        esMovieService.add(movie1);
        return super.update(movie1);
    }

    @Override
    public Movie1 update(Movie1 movie1, String... ignoreProperties) {
        esMovieService.add(movie1);
        return super.update(movie1, ignoreProperties);
    }

    @Override
    public void delete(Long id) {
        esMovieService.remove(id);
        super.delete(id);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id:ids) {
            esMovieService.remove(id);
        }
        super.delete(ids);
    }

    @Override
    public void delete(Movie1 movie1) {
        esMovieService.remove(movie1);
        super.delete(movie1);
    }

    @Override
    public Movie1 find(Long id) {
        return super.find(id);
    }


    @Override
    public Movie1 find1(Long id) {
        return super.find(id);
    }

    @Override
    public Movie1 findByVideoId(String videoId) {
        return movie1Dao.find("videoId",videoId);
    }

    @Override
    public Movie1 findByTitle(String title) {
        return movie1Dao.find("title",title);
    }

    @Override
    public void sync() {
        Integer page = 1;
        JsonRootBean sync = Demo1.sync(page);
        int pagecount = sync.getPagecount();
        System.out.println("============================:"+pagecount);
        List<Data> data = sync.getData();
        save(data);
        page++;
        while (page<=pagecount){
            System.out.println("============================:"+page);
            sync = Demo1.sync(page);
            save(sync.getData());
            page++;
        }

    }

    private void save(List<com.bootx.vo.okzy.com.jsoncn.pojo.Data> data) {

        for (com.bootx.vo.okzy.com.jsoncn.pojo.Data listItem:data) {
            Long type_id = listItem.getType_id();
            MovieCategory movieCategory = movieCategoryService.findByOtherId("okzy_"+type_id);
            if(movieCategory==null){
                movieCategory = new MovieCategory();
                movieCategory.setIsShow(true);
                movieCategory.setOtherId(type_id+"");
                movieCategory.setName(listItem.getType_name());
                movieCategory.setGrade(0);
                movieCategory.setTreePath(",");
                movieCategory = movieCategoryService.save(movieCategory);
            }
            Movie1 movie1 = findByVideoId("okzy_"+listItem.getVod_id());
            if(movie1==null){
                movie1 = new Movie1();
                System.out.println("============================================================================新增============================:"+listItem.getVod_name());
            }else{
                System.out.println(movie1.getId()+"更新============================:"+listItem.getVod_name());
            }
            movie1.setEnglish(listItem.getVod_en());
            movie1.setPlayFrom(listItem.getVod_play_from());
            movie1.setRemarks(listItem.getVod_remarks());
            movie1.setTime(DateUtils.formatStringToDate(listItem.getVod_time(),"yyyy-MM-dd HH:mm:ss"));
            movie1.setTitle(listItem.getVod_name());
            movie1.setVideoId("okzy_"+listItem.getVod_id());
            movie1.setMovieCategory(movieCategory);
            copy(movie1,listItem);
            if(movie1.getId()==null){
                movie1.setIsShow(true);
                try {
                    save(movie1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try {
                    update(movie1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Movie1 copy(Movie1 movie1, Data data) {
        jdbcTemplate.update("delete from playurl where movie1_id="+movie1.getId());
        jdbcTemplate.update("delete from downloadurl where movie1_id="+movie1.getId());
        movie1.setEnglish(data.getVod_en());
        movie1.setActor(data.getVod_actor());
        movie1.setDirector(data.getVod_director());
        movie1.setArea(data.getVod_area());
        movie1.setAddTime(new Date(data.getVod_time_add()*1000));
        movie1.setContent(data.getVod_content());
        movie1.setLang(data.getVod_lang());
        movie1.setPic(data.getVod_pic());
        movie1.setSub(data.getVod_sub());
        movie1.setYear(data.getVod_year());
        movie1.setScore(data.getVod_score());
        String vod_play_url = data.getVod_play_url();
        movie1.setPlayUrls(parsePlayUrl(vod_play_url,movie1));
        String vod_down_url = data.getVod_down_url();
        movie1.setDownloadUrls(parseDownloadUrl(vod_down_url,movie1));
        movie1.setBlurb(data.getVod_blurb());
        movie1.setStatus(data.getVod_status());
        return movie1;
    }

    @Override
    public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
        StringBuffer sb = new StringBuffer("select movie1.id,movie1.videoId,movie1.createdDate,movie1.time,movie1.addTime,movie1.isShow,movie1.lang,movie1.remarks,movie1.score,movie1.title,movie1.year,movie1.movieCategory_id movieCategoryId,moviecategory.`name` movieCategoryName from movie1,moviecategory where moviecategory.id=movie1.movieCategory_id");
        StringBuffer totalSql = new StringBuffer("select count(1) from movie1");

        if(StringUtils.isNotBlank(pageable.getOrderProperty())&&pageable.getOrderDirection()!=null){
            sb.append(" ORDER BY "+pageable.getOrderProperty()+" "+pageable.getOrderDirection().name());
        }else{
            sb.append(" ORDER BY time desc");
        }
        sb.append(" LIMIT "+(pageable.getPageNumber()-1)*pageable.getPageSize()+","+pageable.getPageSize());

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
        Long total = jdbcTemplate.queryForObject(totalSql.toString(),Long.class);

        return new Page(list,total,pageable);
    }

    @Override
    public Map<String, Object> findJdbc(Long id) {
        return null;
    }

    private Set<DownloadUrl> parseDownloadUrl(String vod_down_url, Movie1 movie1) {
        String playFrom = movie1.getPlayFrom();
        String regEx="[$]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(playFrom);
        String [] playFroms = m.replaceAll("@").split("@@@");
        List<DownloadUrl> downloadUrls = new ArrayList<>();
        String[] playUrlStrArray = vod_down_url.split("@@@@@@@@@");
        for (int i=0;i<playUrlStrArray.length;i++){
            DownloadUrl playUrl = new DownloadUrl();
            playUrl.setTitle(playFroms[i]);
            playUrl.setMovie1(movie1);
            String playUrlStr = playUrlStrArray[i];
            String[] playUrlDetails = playUrlStr.split("#");
            List<String> urls = Arrays.asList(playUrlDetails);
            playUrl.setUrls(urls);
            downloadUrls.add(playUrl);
        }
        return new HashSet<>(downloadUrls);

    }

    private Set<PlayUrl> parsePlayUrl(String vodPlayUrl, Movie1 movie1){
        List<PlayUrl> playUrls = new ArrayList<>();
        String[] playUrlStrArray = vodPlayUrl.split("@@@@@@@@@");
        for (int i=0;i<playUrlStrArray.length;i++){
            PlayUrl playUrl = new PlayUrl();
            playUrl.setTitle("线路"+(i+1));
            playUrl.setMovie1(movie1);
            playUrl.setIsEnabled(true);
            String playUrlStr = playUrlStrArray[i];
            String[] playUrlDetails = playUrlStr.split("#");
            List<String> urls = Arrays.asList(playUrlDetails);
            playUrl.setUrls(urls);
            playUrls.add(playUrl);
        }
        return new HashSet<>(playUrls);
    }
}