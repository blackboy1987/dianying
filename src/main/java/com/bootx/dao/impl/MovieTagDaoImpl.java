
package com.bootx.dao.impl;

import com.bootx.dao.MovieTagDao;
import com.bootx.entity.MovieTag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Dao - 文章标签
 * 
 * @author bootx Team
 * @version 6.1
 */
@Repository
public class MovieTagDaoImpl extends BaseDaoImpl<MovieTag, Long> implements MovieTagDao {

    @Override
    public List<MovieTag> findRoots(Integer count) {
        String jpql = "select movieTag from MovieTag movieTag where movieTag.parent is null order by movieTag.order asc";
        TypedQuery<MovieTag> query = entityManager.createQuery(jpql, MovieTag.class);
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }

    @Override
    public List<MovieTag> findParents(MovieTag movieTag, boolean recursive, Integer count) {
        if (movieTag == null || movieTag.getParent() == null) {
            return Collections.emptyList();
        }
        TypedQuery<MovieTag> query;
        if (recursive) {
            String jpql = "select movieTag from MovieTag movieTag where movieTag.id in (:ids) order by movieTag.grade asc";
            query = entityManager.createQuery(jpql, MovieTag.class).setParameter("ids", Arrays.asList(movieTag.getParentIds()));
        } else {
            String jpql = "select movieTag from MovieTag movieTag where movieTag = :movieTag";
            query = entityManager.createQuery(jpql, MovieTag.class).setParameter("movieTag", movieTag.getParent());
        }
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }

    @Override
    public List<MovieTag> findChildren(MovieTag movieTag, boolean recursive, Integer count) {
        TypedQuery<MovieTag> query;
        if (recursive) {
            if (movieTag != null) {
                String jpql = "select movieTag from MovieTag movieTag where movieTag.treePath like :treePath order by movieTag.grade asc, movieTag.order asc";
                query = entityManager.createQuery(jpql, MovieTag.class).setParameter("treePath", "%" + MovieTag.TREE_PATH_SEPARATOR + movieTag.getId() + MovieTag.TREE_PATH_SEPARATOR + "%");
            } else {
                String jpql = "select movieTag from MovieTag movieTag order by movieTag.grade asc, movieTag.order asc";
                query = entityManager.createQuery(jpql, MovieTag.class);
            }
            if (count != null) {
                query.setMaxResults(count);
            }
            List<MovieTag> result = query.getResultList();
            sort(result);
            return result;
        } else {
            String jpql = "select movieTag from MovieTag movieTag where movieTag.parent = :parent order by movieTag.order asc";
            query = entityManager.createQuery(jpql, MovieTag.class).setParameter("parent", movieTag);
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
    private void sort(List<MovieTag> movieCategories) {
        if (CollectionUtils.isEmpty(movieCategories)) {
            return;
        }
        final Map<Long, Integer> orderMap = new HashMap<>();
        for (MovieTag movieTag : movieCategories) {
            orderMap.put(movieTag.getId(), movieTag.getOrder());
        }
        Collections.sort(movieCategories, new Comparator<MovieTag>() {
            @Override
            public int compare(MovieTag movieTag1, MovieTag movieTag2) {
                Long[] ids1 = (Long[]) ArrayUtils.add(movieTag1.getParentIds(), movieTag1.getId());
                Long[] ids2 = (Long[]) ArrayUtils.add(movieTag2.getParentIds(), movieTag2.getId());
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
                        compareToBuilder.append(movieTag1.getGrade(), movieTag2.getGrade());
                    }
                }
                return compareToBuilder.toComparison();
            }
        });
    }
}