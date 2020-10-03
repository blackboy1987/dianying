
package com.bootx.service.impl;

import com.bootx.common.Constants;
import com.bootx.config.ElasticSearchConfig;
import com.bootx.service.ESService;
import com.bootx.util.JsonUtils;
import com.bootx.vo.es.MovieES;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private static final BulkRequest bulkRequest = new BulkRequest();

    @Override
    public boolean put(MovieES movieES) throws IOException {
        if(movieES!=null){
            IndexRequest indexRequest = new IndexRequest(Constants.MOVIE_INDEX);
            indexRequest.id(movieES.getId()+"");
            String json = JsonUtils.toJson(movieES);
            indexRequest.source(json, XContentType.JSON);
            bulkRequest.add(indexRequest);
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
            boolean b = bulk.hasFailures();
            return b;
        }

        return false;

    }
}