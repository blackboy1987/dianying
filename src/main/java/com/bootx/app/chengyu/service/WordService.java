
package com.bootx.app.chengyu.service;

import com.bootx.app.chengyu.entity.Word;
import com.bootx.service.BaseService;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface WordService extends BaseService<Word,Long> {

    boolean exist(String word);
}