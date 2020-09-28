
package com.bootx.service;

import com.bootx.entity.MovieCategory;

import java.util.List;

/**
 * Service - 文章分类
 * 
 * @author bootx Team
 * @version 6.1
 */
public interface MovieCategoryService extends BaseService<MovieCategory, Long> {

	/**
	 * 查找顶级文章分类
	 * 
	 * @return 顶级文章分类
	 */
	List<MovieCategory> findRoots();

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级文章分类
	 */
	List<MovieCategory> findRoots(Integer count);

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @param useCache
	 *            是否使用缓存
	 * @return 顶级文章分类
	 */
	List<MovieCategory> findRoots(Integer count, boolean useCache);

	/**
	 * 查找上级文章分类
	 * 
	 * @param movieCategory
	 *            文章分类
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @return 上级文章分类
	 */
	List<MovieCategory> findParents(MovieCategory movieCategory, boolean recursive, Integer count);

	/**
	 * 查找上级文章分类
	 * 
	 * @param movieCategoryId
	 *            文章分类ID
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @param useCache
	 *            是否使用缓存
	 * @return 上级文章分类
	 */
	List<MovieCategory> findParents(Long movieCategoryId, boolean recursive, Integer count, boolean useCache);

	/**
	 * 查找文章分类树
	 * 
	 * @return 文章分类树
	 */
	List<MovieCategory> findTree();

	/**
	 * 查找下级文章分类
	 * 
	 * @param movieCategory
	 *            文章分类
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @return 下级文章分类
	 */
	List<MovieCategory> findChildren(MovieCategory movieCategory, boolean recursive, Integer count);

	/**
	 * 查找下级文章分类
	 * 
	 * @param movieCategoryId
	 *            文章分类ID
	 * @param recursive
	 *            是否递归
	 * @param count
	 *            数量
	 * @param useCache
	 *            是否使用缓存
	 * @return 下级文章分类
	 */
	List<MovieCategory> findChildren(Long movieCategoryId, boolean recursive, Integer count, boolean useCache);

	MovieCategory findByName(String name);
}