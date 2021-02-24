
package com.bootx.dao.impl;

import com.bootx.dao.PlayUrlDao;
import com.bootx.entity.Movie1;
import com.bootx.entity.PlayUrl;
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
public class PlayUrlDaoImpl extends BaseDaoImpl<PlayUrl, Long> implements PlayUrlDao {

    @Override
    public List<PlayUrl> findListByMovie(Movie1 movie1) {
        if(movie1==null){
            return Collections.emptyList();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PlayUrl> criteriaQuery = criteriaBuilder.createQuery(PlayUrl.class);
        Root<PlayUrl> root = criteriaQuery.from(PlayUrl.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("movie1"), movie1));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isEnabled"), true));
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery);
    }
}