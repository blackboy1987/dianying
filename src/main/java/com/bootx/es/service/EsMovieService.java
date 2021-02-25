package com.bootx.es.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.Movie1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EsMovieService extends EsBaseService {

    void add(Movie1 movie1);

    void remove(Movie1 movie1);

    void remove(Long id);

    Page<Movie1> findPage(Pageable pageable, String name) throws IOException;

    List<Map<String,Object>> list(String hotMovies) throws IOException;

    Movie1 find(Long id);

    void clear() throws IOException;
}