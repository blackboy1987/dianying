
package com.bootx.app.chengyu.service;

import com.bootx.app.chengyu.entity.IdiomLevel;
import com.bootx.service.BaseService;

import java.util.Map;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface IdiomLevelService extends BaseService<IdiomLevel,Long> {

    IdiomLevel findByLevel(Integer level);

    boolean exist(String word);

    void addCache(IdiomLevel idiomLevel);

    Map<String,Object> get(int i);
}