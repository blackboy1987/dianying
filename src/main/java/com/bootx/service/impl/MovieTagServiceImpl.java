
package com.bootx.service.impl;

import com.bootx.common.Filter;
import com.bootx.common.Order;
import com.bootx.dao.MovieTagDao;
import com.bootx.entity.MovieTag;
import com.bootx.service.MovieTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service - 文章标签
 * 
 * @author bootx Team
 * @version 6.1
 */
@Service
public class MovieTagServiceImpl extends BaseServiceImpl<MovieTag, Long> implements MovieTagService {

	@Autowired
	private MovieTagDao movieTagDao;

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "movieTag", condition = "#useCache")
	public List<MovieTag> findList(Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		return movieTagDao.findList(null, count, filters, orders);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public MovieTag save(MovieTag movieTag) {
		return super.save(movieTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public MovieTag update(MovieTag movieTag) {
		return super.update(movieTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public MovieTag update(MovieTag movieTag, String... ignoreProperties) {
		return super.update(movieTag, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "movieTag", allEntries = true)
	public void delete(MovieTag movieTag) {
		super.delete(movieTag);
	}

}