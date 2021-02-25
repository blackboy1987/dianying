package com.bootx.es.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;
import com.bootx.entity.MovieCategory;
import com.bootx.entity.PlayUrl;
import com.bootx.es.EsMovie;
import com.bootx.es.EsMovieRepository;
import com.bootx.es.service.EsMovieService;
import com.bootx.service.DownloadUrlService;
import com.bootx.service.MovieCategoryService;
import com.bootx.service.PlayUrlService;
import com.bootx.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EsMovieServiceImpl extends EsBaseServiceImpl implements EsMovieService {

    @Resource
    private EsMovieRepository esMovieRepository;
    @Resource
    private MovieCategoryService movieCategoryService;
    @Resource
    private PlayUrlService playUrlService;
    @Resource
    private DownloadUrlService downloadUrlService;

    @Override
    @Async
    public void add(Movie1 movie1) {
        EsMovie esMovie = new EsMovie();
        MovieCategory movieCategory = movie1.getMovieCategory();
        MovieCategory parent = movie1.getMovieCategory().getParent();
        esMovie.setId(movie1.getId());
        esMovie.setTitle(movie1.getTitle());
        esMovie.setEnglish(movie1.getEnglish());
        esMovie.setRemarks(movie1.getRemarks());
        esMovie.setArea(movie1.getArea());
        esMovie.setBlurb(movie1.getBlurb());
        if(parent!=null){
            parent = movieCategoryService.find(parent.getId());
            esMovie.setMovieCategoryParentId(parent.getId());
            esMovie.setMovieCategoryParentName(parent.getName());
        }
        esMovie.setMovieCategoryId(movieCategory.getId());
        esMovie.setMovieCategoryName(movieCategory.getName());
        esMovie.setMovieCategoryTreePath(movieCategory.getTreePath()+movieCategory.getId()+",");
        esMovie.setSub(movie1.getSub());
        esMovie.setPic(movie1.getPic());
        esMovie.setActor(movie1.getActor());
        esMovie.setDirector(movie1.getDirector());
        esMovie.setLang(movie1.getLang());
        esMovie.setTime(movie1.getTime());
        esMovie.setUpdateTime(movie1.getUpdateTime());
        esMovie.setAddTime(movie1.getAddTime());
        esMovie.setContent(movie1.getContent());
        esMovie.setYear(movie1.getYear());
        List<PlayUrl> playUrls = playUrlService.findListByMovie(movie1);
        if(playUrls!=null&&playUrls.size()>0){
            esMovie.setPlayUrls(JsonUtils.toJson(playUrls));
        }
        List<DownloadUrl> downloadUrls = downloadUrlService.findListByMovie(movie1);
        if(downloadUrls!=null&&downloadUrls.size()>0){
            esMovie.setDownloadUrls(JsonUtils.toJson(downloadUrls));
        }
        esMovie.setScore(movie1.getScore());
        esMovie.setIsShow(true);

        esMovie.setUpdateTime(movie1.getUpdateTime());
        esMovieRepository.save(esMovie);
    }

    @Override
    @Async
    public void remove(Movie1 movie1) {
        esMovieRepository.delete(esMovieRepository.findById(movie1.getId()).get());
    }

    @Override
    @Async
    public void remove(Long id) {
        esMovieRepository.delete(esMovieRepository.findById(id).get());
    }

    @Override
    public Page<Movie1> findPage(Pageable pageable, String name) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        List<Map<String,Object>> esPddCrawlerProducts = new ArrayList<>();
        searchRequest.indices(EsMovie.ES_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StringUtils.isNotBlank(name)){
            boolQueryBuilder.must(QueryBuilders.matchQuery("name",name));
            HighlightBuilder highlightBuilder=new HighlightBuilder();
            highlightBuilder.preTags("<span class=\"keywords\">");
            highlightBuilder.postTags("</span>");
            highlightBuilder.field("name");
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort("createdDate", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit searchHit:hits1) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            for (String key:highlightFields.keySet()) {
                sourceAsMap.put(key,highlightFields.get(key).getFragments()[0].string());
            }
            esPddCrawlerProducts.add(sourceAsMap);
        }

        return new Page(esPddCrawlerProducts,hits.getTotalHits().value,pageable);
    }

    @Override
    public List<Map<String,Object>> list(String tagName) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        List<Map<String,Object>> esPddCrawlerProducts = new ArrayList<>();
        searchRequest.indices(EsMovie.ES_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(new String[]{
                "id","pic","lang","title","remarks","movieCategoryName","score"
        },null);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(30);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StringUtils.equalsAnyIgnoreCase(tagName,"hotMovies")){
            MatchQueryBuilder queryBuilder1 = QueryBuilders.matchQuery("movieCategoryId", 1);
            MatchQueryBuilder queryBuilder6 = QueryBuilders.matchQuery("movieCategoryId", 6);
            MatchQueryBuilder queryBuilder7 = QueryBuilders.matchQuery("movieCategoryId", 7);
            MatchQueryBuilder queryBuilder8 = QueryBuilders.matchQuery("movieCategoryId", 8);
            MatchQueryBuilder queryBuilder9 = QueryBuilders.matchQuery("movieCategoryId", 9);
            MatchQueryBuilder queryBuilder10 = QueryBuilders.matchQuery("movieCategoryId", 10);
            MatchQueryBuilder queryBuilder11 = QueryBuilders.matchQuery("movieCategoryId", 11);
            MatchQueryBuilder queryBuilder12 = QueryBuilders.matchQuery("movieCategoryId", 12);
            boolQueryBuilder.should(queryBuilder1);
            boolQueryBuilder.should(queryBuilder6);
            boolQueryBuilder.should(queryBuilder7);
            boolQueryBuilder.should(queryBuilder8);
            boolQueryBuilder.should(queryBuilder9);
            boolQueryBuilder.should(queryBuilder10);
            boolQueryBuilder.should(queryBuilder11);
            boolQueryBuilder.should(queryBuilder12);
        }else if(StringUtils.equalsAnyIgnoreCase(tagName,"hottv")){
            MatchQueryBuilder queryBuilder2 = QueryBuilders.matchQuery("movieCategoryId", 2);
            MatchQueryBuilder queryBuilder13 = QueryBuilders.matchQuery("movieCategoryId", 13);
            MatchQueryBuilder queryBuilder14 = QueryBuilders.matchQuery("movieCategoryId", 14);
            MatchQueryBuilder queryBuilder15 = QueryBuilders.matchQuery("movieCategoryId", 15);
            MatchQueryBuilder queryBuilder16 = QueryBuilders.matchQuery("movieCategoryId", 16);
            boolQueryBuilder.should(queryBuilder2);
            boolQueryBuilder.should(queryBuilder13);
            boolQueryBuilder.should(queryBuilder14);
            boolQueryBuilder.should(queryBuilder15);
            boolQueryBuilder.should(queryBuilder16);
        }else if(StringUtils.equalsAnyIgnoreCase(tagName,"news")){

        }
        boolQueryBuilder.must(QueryBuilders.matchQuery("isShow",true));

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort("time", SortOrder.DESC);
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
    public Movie1 find(Long id) {
        Movie1 movie1 = new Movie1();
        EsMovie esMovie = esMovieRepository.findById(id).get();
        if(esMovie!=null){
            BeanUtils.copyProperties(esMovie,movie1,"playUrls","downloadUrls");
            movie1.setPlayUrls(JsonUtils.toObject(esMovie.getPlayUrls(), new TypeReference<Set<PlayUrl>>() {
            }));
            movie1.setDownloadUrls(JsonUtils.toObject(esMovie.getDownloadUrls(), new TypeReference<Set<DownloadUrl>>() {
            }));
        }
        return movie1;
    }

    @Override
    public void clear() {
        esMovieRepository.deleteAll();
    }


}