package com.bootx.service.api.impl;

import com.bootx.service.api.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable(value="siteInfo",key = "#appCode +'-'+ #appSecret",sync=true)
    public Map<String,Object> site(String appCode, String appSecret){
        StringBuffer sb =new StringBuffer();

        sb.append("select ");
        sb.append("siteInfo.logo, ");
        sb.append("siteInfo.bannerAdId, ");
        sb.append("siteInfo.gridAdId, ");
        sb.append("siteInfo.interstitialAdId, ");
        sb.append("siteInfo.nativeAdId, ");
        sb.append("siteInfo.rewardedVideoAdId, ");
        sb.append("siteInfo.videoAdId, ");
        sb.append("siteInfo.videoFrontAdId, ");
        sb.append("siteInfo.name ");
        sb.append("from ");
        sb.append("siteInfo as siteInfo, ");
        sb.append("app as app ");
        sb.append("where app.appCode='"+appCode+"' ");
        sb.append("and app.token='"+appSecret+"' ");
        sb.append("and siteInfo.appId=app.id");
        try {
            return jdbcTemplate.queryForMap(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable(value = "movie",key = "'list_'+#categoryId +'_'+ #pageNumber")
    public Object list(Long categoryId,Integer pageNumber){
        Map<String,Object> data = new HashMap<>();
        if(categoryId==null){
            StringBuffer sb = new StringBuffer();
            sb.append("id,");
            sb.append("area,");
            sb.append("img,");
            sb.append("lang,");
            sb.append("title,");
            sb.append("score");

            data.put("hotMovies", jdbcTemplate.queryForList("select "+sb.toString()+" from movie limit 18"));
            data.put("hottv", jdbcTemplate.queryForList("select "+sb.toString()+" from movie limit 18"));
            data.put("news", jdbcTemplate.queryForList("select "+sb.toString()+" from movie limit 18"));
            return data;
        }
        return movies(categoryId,pageNumber);
    }

    private List<Map<String,Object>> movies(Long categoryId, Integer pageNumber) {
        if(categoryId==null){
            return Collections.emptyList();
        }
        if(pageNumber==null || pageNumber<0){
            pageNumber = 1;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append("id,");
        sb.append("area,");
        sb.append("img,");
        sb.append("lang,");
        sb.append("title,");
        sb.append("score");
        sb.append(" from");
        sb.append(" movie as movie,");

        if(categoryId>10000){
            sb.append(" movie_movietags as movieTag");
            sb.append(" where 1=1");
            sb.append(" and movieTag.movieTags_id=").append(categoryId);
            sb.append(" and movieTag.movies_id=movie.id");
        }else{
            sb.append(" movie_moviecategories as moveTag");
            sb.append(" where 1=1");
            sb.append(" and moveTag.movieCategories_id=").append(categoryId);
            sb.append(" and moveTag.movies_id=movie.id");
        }
        sb.append(" limit "+(pageNumber-1)*18+", 18");
        return jdbcTemplate.queryForList(sb.toString());


    }
}
