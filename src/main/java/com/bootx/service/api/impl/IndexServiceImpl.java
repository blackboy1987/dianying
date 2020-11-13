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
        Map<String,Object> data = new HashMap<>();
        sb.append("select ");
        sb.append("siteInfo.logo, ");
        sb.append("siteInfo.openAd, ");
        sb.append("siteInfo.bannerAdId, ");
        sb.append("siteInfo.gridAdId, ");
        sb.append("siteInfo.interstitialAdId, ");
        sb.append("siteInfo.nativeAdId, ");
        sb.append("siteInfo.rewardedVideoAdId, ");
        sb.append("siteInfo.videoAdId, ");
        sb.append("siteInfo.videoFrontAdId, ");
        sb.append("siteInfo.status, ");
        sb.append("siteInfo.everyMinuteToPoint, ");
        sb.append("siteInfo.jumpAdDiscoutPoint, ");
        sb.append("siteInfo.minVisitMinute, ");
        sb.append("siteInfo.openPoint, ");
        sb.append("siteInfo.name ");
        sb.append("from ");
        sb.append("siteInfo as siteInfo, ");
        sb.append("app as app ");
        sb.append("where app.appCode='"+appCode+"' ");
        sb.append("and app.token='"+appSecret+"' ");
        sb.append("and siteInfo.appId=app.id");

        try {
            data = jdbcTemplate.queryForMap(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        // 处理轮播图。最近更新的十部
        StringBuffer sb1 = new StringBuffer();
        sb1.append("id,");
        sb1.append("pic,");
        sb1.append("title");
        data.put("carousels",jdbcTemplate.queryForList("select "+sb1.toString()+" from movie1 where isShow=true order by score desc limit 10"));
        return data;
    }

    @Override
    @Cacheable(value = "movie",key = "'list_'+#categoryId +'_'+ #pageNumber")
    public Object list(Long categoryId,Integer pageNumber){
        Map<String,Object> data = new HashMap<>();
        if(categoryId==null){
            StringBuffer sb = new StringBuffer();
            sb.append("movie.id,");
            sb.append("movie.area,");
            sb.append("movie.pic,");
            sb.append("movie.lang,");
            sb.append("movie.title,");
            sb.append("movie.remarks,");
            sb.append("movieCategory.name typeName,");
            sb.append("movie.score");
            data.put("hotMovies", jdbcTemplate.queryForList("select "+sb.toString()+" from movie1 as movie,moviecategory as movieCategory where movie.isShow=true and movie.movieCategory_id=movieCategory.id and ( movieCategory.id = 1 OR movieCategory.treePath like '%,1,%') order by movie.score desc LIMIT 30"));
            data.put("hottv", jdbcTemplate.queryForList("select "+sb.toString()+" from movie1 as movie,moviecategory as movieCategory where movie.isShow=true and movie.movieCategory_id=movieCategory.id and ( movieCategory.id = 2 OR movieCategory.treePath like '%,2,%') order by movie.score desc LIMIT 30"));
            data.put("news", jdbcTemplate.queryForList("select "+sb.toString()+" from movie1 as movie,moviecategory as movieCategory where movie.isShow=true and movie.movieCategory_id=movieCategory.id order by movie.updateTime desc limit 30"));
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
        sb.append("movie.id,");
        sb.append("movie.area,");
        sb.append("movie.pic,");
        sb.append("movie.lang,");
        sb.append("movie.title,");
        sb.append("movie.remarks,");
        sb.append("movieCategory.name typeName,");
        sb.append("movie.score");
        sb.append(" from");
        sb.append(" movie1 as movie,");

        if(categoryId>10000){
            sb.append(" movie_movietags as movieTag");
            sb.append(" where 1=1");
            sb.append(" and movieTag.movieTags_id=").append(categoryId);
            sb.append(" and movieTag.movies_id=movie.id");
        }else{
            sb.append("moviecategory as movieCategory");
            sb.append(" where 1=1");
            sb.append(" and ");
            sb.append("  movie.isShow=true and ");
            sb.append(" movie.movieCategory_id=movieCategory.id ");
            sb.append(" and ");
            sb.append( " ( movieCategory.id = "+categoryId+" OR movieCategory.treePath like '%,"+categoryId+",%') ");
        }
        sb.append(" order by movie.score desc limit "+(pageNumber-1)*30+", 30");
        return jdbcTemplate.queryForList(sb.toString());
    }
}
