
package com.bootx.service;


import com.bootx.common.Filter;
import com.bootx.common.Order;
import com.bootx.entity.MovieTag;

import java.util.List;

/**
 * Service - 文章标签
 * 
 * @author bootx Team
 * @version 6.1
 */
public interface MovieTagService extends BaseService<MovieTag, Long> {

	/**
	 * 查找文章标签
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param useCache
	 *            是否使用缓存
	 * @return 文章标签
	 */
	List<MovieTag> findList(Integer count, List<Filter> filters, List<Order> orders, boolean useCache);

	MovieTag findByName(String name);
}