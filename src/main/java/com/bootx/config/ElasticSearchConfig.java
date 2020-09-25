package com.bootx.config;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }


    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("127.0.0.1",9200,"http"));

        RestHighLevelClient restHighLevelClient =  new RestHighLevelClient(restClientBuilder);

        return restHighLevelClient;
    }
}
