
package com.bootx.service.impl;

import com.bootx.dao.MovieCategoryDao;
import com.bootx.entity.MovieCategory;
import com.bootx.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Service - 文章分类
 * 
 * @author bootx Team
 * @version 6.1
 */
@Service
public class MovieCategoryServiceImpl extends BaseServiceImpl<MovieCategory, Long> implements MovieCategoryService {

	@Autowired
	private MovieCategoryDao movieCategoryDao;

	@Override
	@Transactional(readOnly = true)
	public List<MovieCategory> findRoots() {
		return movieCategoryDao.findRoots(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieCategory> findRoots(Integer count) {
		return movieCategoryDao.findRoots(count);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "movieCategory", condition = "#useCache")
	public List<MovieCategory> findRoots(Integer count, boolean useCache) {
		return movieCategoryDao.findRoots(count);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieCategory> findParents(MovieCategory movieCategory, boolean recursive, Integer count) {
		return movieCategoryDao.findParents(movieCategory, recursive, count);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "movieCategory", condition = "#useCache")
	public List<MovieCategory> findParents(Long movieCategoryId, boolean recursive, Integer count, boolean useCache) {
		MovieCategory movieCategory = movieCategoryDao.find(movieCategoryId);
		if (movieCategoryId != null && movieCategory == null) {
			return Collections.emptyList();
		}
		return movieCategoryDao.findParents(movieCategory, recursive, count);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieCategory> findTree() {
		return movieCategoryDao.findChildren(null, true, null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieCategory> findChildren(MovieCategory movieCategory, boolean recursive, Integer count) {
		return movieCategoryDao.findChildren(movieCategory, recursive, count);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "movieCategory", condition = "#useCache")
	public List<MovieCategory> findChildren(Long movieCategoryId, boolean recursive, Integer count, boolean useCache) {
		MovieCategory movieCategory = movieCategoryDao.find(movieCategoryId);
		if (movieCategoryId != null && movieCategory == null) {
			return Collections.emptyList();
		}
		return movieCategoryDao.findChildren(movieCategory, recursive, count);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public MovieCategory save(MovieCategory movieCategory) {
		Assert.notNull(movieCategory, "[Assertion failed] - movieCategory is required; it must not be null");

		setValue(movieCategory);
		return super.save(movieCategory);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public MovieCategory update(MovieCategory movieCategory) {
		Assert.notNull(movieCategory, "[Assertion failed] - movieCategory is required; it must not be null");

		setValue(movieCategory);
		for (MovieCategory children : movieCategoryDao.findChildren(movieCategory, true, null)) {
			setValue(children);
		}
		return super.update(movieCategory);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public MovieCategory update(MovieCategory movieCategory, String... ignoreProperties) {
		return super.update(movieCategory, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "movie", "movieCategory" }, allEntries = true)
	public void delete(MovieCategory movieCategory) {
		super.delete(movieCategory);
	}

	/**
	 * 设置值
	 * 
	 * @param movieCategory
	 *            文章分类
	 */
	private void setValue(MovieCategory movieCategory) {
		if (movieCategory == null) {
			return;
		}
		MovieCategory parent = movieCategory.getParent();
		if (parent != null) {
			movieCategory.setTreePath(parent.getTreePath() + parent.getId() + MovieCategory.TREE_PATH_SEPARATOR);
		} else {
			movieCategory.setTreePath(MovieCategory.TREE_PATH_SEPARATOR);
		}
		movieCategory.setGrade(movieCategory.getParentIds().length);
	}

}