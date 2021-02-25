package com.bootx.es.service.impl;

import com.bootx.es.EsMovie;
import com.bootx.es.service.EsSearchService;
import com.bootx.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EsSearchServiceImpl extends EsBaseServiceImpl implements EsSearchService {

    @Override
    public List<Map<String,Object>> search(String keywords,Integer page) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        List<Map<String,Object>> esPddCrawlerProducts = new ArrayList<>();
        searchRequest.indices(EsMovie.ES_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(new String[]{
                "id","pic","title","remarks","year","actor","area","movieCategoryName","actor","blurb"
        },null);
        if(page==null||page<=0){
            page = 1;
        }

        searchSourceBuilder.from((page-1)*50);
        searchSourceBuilder.size(50);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StringUtils.isNotBlank(keywords)){
            boolQueryBuilder.must(QueryBuilders.matchQuery("title",keywords));
            HighlightBuilder highlightBuilder=new HighlightBuilder();
            highlightBuilder.preTags("<span class=\"keywords\">");
            highlightBuilder.postTags("</span>");
            highlightBuilder.field("name");
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery("isShow",true));

        searchSourceBuilder.query(boolQueryBuilder);
        // searchSourceBuilder.sort("time", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit searchHit:hits1) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            sourceAsMap.put("typeName",sourceAsMap.get("movieCategoryName"));
            esPddCrawlerProducts.add(sourceAsMap);
        }

        return esPddCrawlerProducts;
    }

    @Override
    public List<Map<String,Object>> search(Long tabListId,Long categoryId,String areaName,String yearName,Integer sortIndex,Integer page) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        List<Map<String,Object>> esPddCrawlerProducts = new ArrayList<>();
        searchRequest.indices(EsMovie.ES_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(new String[]{
                "id","pic","title","remarks","year","actor","area","movieCategoryName"
        },null);
        if(page==null||page<=0){
            page = 1;
        }

        searchSourceBuilder.from((page-1)*30);
        searchSourceBuilder.size(30);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(tabListId!=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("movieCategoryParentId",tabListId));
        }
        if(categoryId!=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("movieCategoryId",categoryId));
        }
        if(StringUtils.isNotBlank(areaName)){
            boolQueryBuilder.must(QueryBuilders.matchQuery("area",areaName));
        }
        if(StringUtils.isNotBlank(yearName)){
            boolQueryBuilder.must(QueryBuilders.matchQuery("year",yearName));
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery("isShow",true));

        searchSourceBuilder.query(boolQueryBuilder);
        if(sortIndex==null){
            searchSourceBuilder.sort("time", SortOrder.DESC);
        }else if(sortIndex==0){
            searchSourceBuilder.sort("time", SortOrder.DESC);
        }else if(sortIndex==1){
            searchSourceBuilder.sort("time", SortOrder.DESC);
        }else if(sortIndex==2){
            searchSourceBuilder.sort("score", SortOrder.DESC);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JsonUtils.toJson(search));
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit searchHit:hits1) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            esPddCrawlerProducts.add(sourceAsMap);
        }
        return esPddCrawlerProducts;
    }

    @Override
    public List<Map<String, Object>> searchIndex(Long movieCategoryId,Integer from, Integer size,String [] fields) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        List<Map<String,Object>> esPddCrawlerProducts = new ArrayList<>();
        searchRequest.indices(EsMovie.ES_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(fields!=null&&fields.length>0){
            searchSourceBuilder.fetchSource(fields,null);
        }
        if(from==null){
            searchSourceBuilder.from(0);
        }else{
            searchSourceBuilder.from(from);
        }
        if(size==null|| size<=0){
            searchSourceBuilder.size(30);
        }else{
            searchSourceBuilder.size(size);
        }



        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(movieCategoryId!=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("movieCategoryParentId",movieCategoryId));
        }
        boolQueryBuilder.must(QueryBuilders.matchQuery("isShow",true));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit searchHit:hits1) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            esPddCrawlerProducts.add(sourceAsMap);
        }
        return esPddCrawlerProducts;
    }
}