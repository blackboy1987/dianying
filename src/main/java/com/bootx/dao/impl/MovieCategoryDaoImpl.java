
package com.bootx.dao.impl;

import com.bootx.dao.MovieCategoryDao;
import com.bootx.entity.MovieCategory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Dao - 文章分类
 * 
 * @author bootx Team
 * @version 6.1
 */
@Repository
public class MovieCategoryDaoImpl extends BaseDaoImpl<MovieCategory, Long> implements MovieCategoryDao {

	@Override
	public List<MovieCategory> findRoots(Integer count) {
		String jpql = "select movieCategory from MovieCategory movieCategory where movieCategory.parent is null order by movieCategory.order asc";
		TypedQuery<MovieCategory> query = entityManager.createQuery(jpql, MovieCategory.class);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<MovieCategory> findParents(MovieCategory movieCategory, boolean recursive, Integer count) {
		if (movieCategory == null || movieCategory.getParent() == null) {
			return Collections.emptyList();
		}
		TypedQuery<MovieCategory> query;
		if (recursive) {
			String jpql = "select movieCategory from MovieCategory movieCategory where movieCategory.id in (:ids) order by movieCategory.grade asc";
			query = entityManager.createQuery(jpql, MovieCategory.class).setParameter("ids", Arrays.asList(movieCategory.getParentIds()));
		} else {
			String jpql = "select movieCategory from MovieCategory movieCategory where movieCategory = :movieCategory";
			query = entityManager.createQuery(jpql, MovieCategory.class).setParameter("movieCategory", movieCategory.getParent());
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<MovieCategory> findChildren(MovieCategory movieCategory, boolean recursive, Integer count) {
		TypedQuery<MovieCategory> query;
		if (recursive) {
			if (movieCategory != null) {
				String jpql = "select movieCategory from MovieCategory movieCategory where movieCategory.treePath like :treePath order by movieCategory.grade asc, movieCategory.order asc";
				query = entityManager.createQuery(jpql, MovieCategory.class).setParameter("treePath", "%" + MovieCategory.TREE_PATH_SEPARATOR + movieCategory.getId() + MovieCategory.TREE_PATH_SEPARATOR + "%");
			} else {
				String jpql = "select movieCategory from MovieCategory movieCategory order by movieCategory.grade asc, movieCategory.order asc";
				query = entityManager.createQuery(jpql, MovieCategory.class);
			}
			if (count != null) {
				query.setMaxResults(count);
			}
			List<MovieCategory> result = query.getResultList();
			sort(result);
			return result;
		} else {
			String jpql = "select movieCategory from MovieCategory movieCategory where movieCategory.parent = :parent order by movieCategory.order asc";
			query = entityManager.createQuery(jpql, MovieCategory.class).setParameter("parent", movieCategory);
			if (count != null) {
				query.setMaxResults(count);
			}
			return query.getResultList();
		}
	}

	/**
	 * 排序文章分类
	 * 
	 * @param movieCategories
	 *            文章分类
	 */
	private void sort(List<MovieCategory> movieCategories) {
		if (CollectionUtils.isEmpty(movieCategories)) {
			return;
		}
		final Map<Long, Integer> orderMap = new HashMap<>();
		for (MovieCategory movieCategory : movieCategories) {
			orderMap.put(movieCategory.getId(), movieCategory.getOrder());
		}
		Collections.sort(movieCategories, new Comparator<MovieCategory>() {
			@Override
			public int compare(MovieCategory movieCategory1, MovieCategory movieCategory2) {
				Long[] ids1 = (Long[]) ArrayUtils.add(movieCategory1.getParentIds(), movieCategory1.getId());
				Long[] ids2 = (Long[]) ArrayUtils.add(movieCategory2.getParentIds(), movieCategory2.getId());
				Iterator<Long> iterator1 = Arrays.asList(ids1).iterator();
				Iterator<Long> iterator2 = Arrays.asList(ids2).iterator();
				CompareToBuilder compareToBuilder = new CompareToBuilder();
				while (iterator1.hasNext() && iterator2.hasNext()) {
					Long id1 = iterator1.next();
					Long id2 = iterator2.next();
					Integer order1 = orderMap.get(id1);
					Integer order2 = orderMap.get(id2);
					compareToBuilder.append(order1, order2).append(id1, id2);
					if (!iterator1.hasNext() || !iterator2.hasNext()) {
						compareToBuilder.append(movieCategory1.getGrade(), movieCategory2.getGrade());
					}
				}
				return compareToBuilder.toComparison();
			}
		});
	}

}