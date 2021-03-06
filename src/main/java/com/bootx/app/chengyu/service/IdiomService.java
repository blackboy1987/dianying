
package com.bootx.app.chengyu.service;

import com.bootx.app.chengyu.entity.Idiom;
import com.bootx.service.BaseService;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface IdiomService extends BaseService<Idiom,Long> {

    boolean exist(String fullName);

    Idiom findByLevel(Integer levle);
}