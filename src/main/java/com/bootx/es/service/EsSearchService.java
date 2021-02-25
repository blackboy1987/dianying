package com.bootx.es.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EsSearchService extends EsBaseService {

    List<Map<String,Object>> search(String keywords,Integer page) throws IOException;

    /**
     *      * tabListId:
     *      * categoryId: 二级分类的id
     *      * areaName: 所在区域
     *      * yearName: 年份（2020)
     *      * sortIndex: 排序方式。0： 按热度降序，1： 更新时间降序。2：评分降序
     *      * @return
     *      */

    /**
     *
     * @param tabListId
     *      一级分类的id
     * @param categoryId
     *      二级分类的id
     * @param areaName
     *      所在区域
     * @param yearName
     *      年份（2020)
     * @param sortIndex
     *      排序方式。0： 按热度降序，1： 更新时间降序。2：评分降序
     * @param page
     *      页码
     * @return
     * @throws IOException
     */
    List<Map<String,Object>> search(Long tabListId,Long categoryId,String areaName,String yearName,Integer sortIndex,Integer page) throws IOException;



    List<Map<String,Object>> searchIndex(Long movieCategoryId,Integer from, Integer size,String [] fields) throws IOException;
}