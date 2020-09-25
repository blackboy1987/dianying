
package com.bootx.service.impl;

import com.bootx.entity.MovieCategory;
import com.bootx.service.IndexService;
import com.bootx.service.MovieCategoryService;
import com.bootx.util.EhCacheUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieCategoryService movieCategoryService;

    private static final String  CACHENAME = "appIndexController";

    @Override
    public List<Map<String,Object>> movies(String type, MovieCategory movieCategory, Integer pageNumber, Integer count){
        String cacheKey = "movies_"+type+"_"+movieCategory+"_"+pageNumber;
        if(count==null){
            count = 12;
        }
        List<Map<String,Object>> data = new ArrayList<>();
        try{
            data = (List<Map<String,Object>>) EhCacheUtils.getCacheValue(CACHENAME,cacheKey);
        }catch (Exception e){
            e.printStackTrace();
            data = movies(type,movieCategory,pageNumber,count,cacheKey);
        }
        return data;
    }


    private List<Map<String,Object>> movies(String type, MovieCategory movieCategory, Integer pageNumber, Integer count,String cacheKey){
        List<Map<String,Object>> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select vod_name,vod_pic,vod_id,vod_lang,vod_remarks from movie.Movie where 1=1");
        String orderBy = "";
        String limit = "";
        if(movieCategory!=null){
            sql.append(" and type_id="+movieCategory.getCategoryId());
        }

        if(StringUtils.equals(type,"hotMovies")){
            sql.append(" and type_name='电影'");
            orderBy=" order by vod_score desc";
            limit = " limit "+count;
        }else if(StringUtils.equals(type,"hottv")){
            sql.append(" and type_name='连续剧'");
            orderBy=" order by vod_score desc";
            limit = " limit "+count;
        }else if(StringUtils.equals(type,"new")){
            orderBy=" order by vod_score desc";
            limit = " limit "+count;
        }
        if(StringUtils.isEmpty(orderBy)){
            orderBy=" order by vod_time desc";
        }
        if(StringUtils.isEmpty(limit)&&pageNumber!=null&&pageNumber>0){
            limit = " limit "+(pageNumber-1)*10+", 10";
        }

        sql.append(orderBy);
        sql.append(limit);
        data = jdbcTemplate.queryForList(sql.toString());
        EhCacheUtils.setCacheValue(CACHENAME,cacheKey,data);
        return data;
    }



    @Override
    public List<Map<String, Object>> categories(){
        String key = "categories";
        List<Map<String, Object>> categories = new ArrayList<>();
        try{
            categories = (List<Map<String,Object>>)EhCacheUtils.getCacheValue(CACHENAME,key);
        }catch (Exception e) {
            e.printStackTrace();
           categories = categories(key);
        }
        return categories;
    }

    private List<Map<String, Object>> categories(String key){
        List<Map<String, Object>> categories = new ArrayList<>();
        List<MovieCategory> movieCategories = movieCategoryService.findAll();
        for (MovieCategory movieCategory : movieCategories) {
            Map<String, Object> mo = new HashMap<>();
            mo.put("id", movieCategory.getId());
            mo.put("name", movieCategory.getName());
            categories.add(mo);
        }
        EhCacheUtils.setCacheValue(CACHENAME,key,categories);
        return categories;
    }

    @Override
    public Map<String,Object> detail(Long id){
        String key = "detail_"+id;
        Map<String,Object> data = detail(id,key);
        /*try{
            data = (Map<String,Object>)EhCacheUtils.getCacheValue(CACHENAME,key);
        }catch (Exception e) {
            data = detail(id,key);
        }*/
        return data;
    }

    private Map<String,Object> detail(Long id,String key){
        StringBuffer sb = new StringBuffer("vod_id,");
        sb.append("vod_actor,");
        sb.append("vod_area,");
        sb.append("vod_author,");
        sb.append("vod_content,");
        sb.append("vod_director,");
        sb.append("vod_lang,");
        sb.append("vod_name,");
        sb.append("vod_play_url,");
        sb.append("vod_remarks,");
        sb.append("type_name,");
        sb.append("vod_score,");
        sb.append("vod_year");


        Map<String,Object> data = jdbcTemplate.queryForMap("select "+sb.toString()+" from movie.Movie where vod_id="+id);
        EhCacheUtils.setCacheValue(CACHENAME,key,data);
        return data;
    }

    @Override
    public void init() {

    }
}