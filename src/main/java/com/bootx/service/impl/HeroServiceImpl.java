
package com.bootx.service.impl;

import com.bootx.dao.HeroDao;
import com.bootx.entity.Hero;
import com.bootx.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class HeroServiceImpl extends BaseServiceImpl<Hero, Long> implements HeroService {

	@Autowired
	private HeroDao heroDao;

	@Override
	public boolean heroIdExist(Long heroId) {
		return heroDao.exists("heroId",heroId);
	}
}