package com.bootx.controller.api.v2;

import com.bootx.common.Result;
import com.bootx.es.service.EsSearchService;
import com.bootx.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController("apiSearchV2Controller")
@RequestMapping("/api/v2/search")
public class SearchController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private EsSearchService esSearchService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * tabListId: 一级分类的id
     * categoryId: 二级分类的id
     * areaName: 所在区域
     * yearName: 年份（2020)
     * sortIndex: 排序方式。0： 按热度降序，1： 更新时间降序。2：评分降序
     * page: 页码
     * @return
     */
    @PostMapping()
    public Result index(Long tabListId,Long categoryId,String areaName,String yearName,Integer sortIndex,Integer page,String keyword) throws IOException {
        if(StringUtils.isNotBlank(keyword)){
            return Result.success(esSearchService.search(keyword,page));
        }
        return Result.success(esSearchService.search(tabListId,categoryId,areaName,yearName,sortIndex,page));
    }

    @PostMapping("/category")
    public Result category(){
        String category = stringRedisTemplate.opsForValue().get("category");
        if(StringUtils.isNotBlank(category)){
            return Result.success(JsonUtils.toObject(category, new TypeReference<List<Map<String,Object>>>() {
            }));
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select first.id,first.name,(select count(child.id) from moviecategory child where child.parent_id=first.id) childCount from moviecategory as first where first.isShow=true and first.parent_id is null order by first.orders asc;");
        list.stream().forEach(item->{
            Integer childCount = Integer.valueOf(item.get("childCount")+"");
            if(childCount>0) {
                item.put("category", jdbcTemplate.queryForList("select id,name,isShow from moviecategory where parent_id=1 and isShow=true order by orders;"));
            }
            item.put("area",jdbcTemplate.queryForList("select count(movie1.area) `count` ,movie1.area name from movie1,moviecategory where moviecategory.id=movie1.movieCategory_id and (moviecategory.treePath like  '%,"+item.get("id")+",%' or movie1.movieCategory_id="+item.get("id")+") group by movie1.area"));
            item.put("year",jdbcTemplate.queryForList("select count(movie1.year) `count`,movie1.year name from movie1,moviecategory where moviecategory.id=movie1.movieCategory_id and (moviecategory.treePath like  '%,"+item.get("id")+",%' or movie1.movieCategory_id="+item.get("id")+") group by movie1.year order by movie1.year desc"));
        });
        stringRedisTemplate.opsForValue().set("category", JsonUtils.toJson(list));
        return Result.success(list);
    }



}
