
package com.bootx.dao.impl;

import com.bootx.dao.DownloadUrlDao;
import com.bootx.entity.DownloadUrl;
import com.bootx.entity.Movie1;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

/**
 * Dao - 插件配置
 * 
 * @author blackboy
 * @version 1.0
 */
@Repository
public class DownloadUrlDaoImpl extends BaseDaoImpl<DownloadUrl, Long> implements DownloadUrlDao {

    @Override
    public List<DownloadUrl> findListByMovie(Movie1 movie1) {
        if(movie1==null){
            return Collections.emptyList();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DownloadUrl> criteriaQuery = criteriaBuilder.createQuery(DownloadUrl.class);
        Root<DownloadUrl> root = criteriaQuery.from(DownloadUrl.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("movie1"), movie1));
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery);
    }
}