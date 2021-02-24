
package com.bootx.dao;

import com.bootx.entity.MovieCategory;

import java.util.List;

/**
 * Dao - 文章分类
 * 
 * @author bootx Team
 * @version 6.1
 */
public interface MovieCategoryDao extends BaseDao<MovieCategory, Long> {

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级文章分类
	 */
	List<MovieCategory> findRoots(Integer count);

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
}