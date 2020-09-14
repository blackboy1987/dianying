
package com.bootx.service;

import com.bootx.entity.Hero;

/**
 * Service - 插件
 * 
 * @author blackboy
 * @version 1.0
 */
public interface HeroService extends BaseService<Hero,Long> {

    boolean heroIdExist(Long heroId);
}