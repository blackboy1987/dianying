package com.bootx.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsMovieRepository extends ElasticsearchRepository<EsMovie, Long> {
}