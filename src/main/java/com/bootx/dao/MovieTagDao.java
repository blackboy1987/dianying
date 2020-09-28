
package com.bootx.dao;

import com.bootx.entity.MovieTag;

import java.util.List;

/**
 * Dao - 文章标签
 * 
 * @author bootx Team
 * @version 6.1
 */
public interface MovieTagDao extends BaseDao<MovieTag, Long> {


    /**
     * 查找顶级文章分类
     *
     * @param count
     *            数量
     * @return 顶级文章分类
     */
    List<MovieTag> findRoots(Integer count);

    /**
     * 查找上级文章分类
     *
     * @param movieTag
     *            文章分类
     * @param recursive
     *            是否递归
     * @param count
     *            数量
     * @return 上级文章分类
     */
    List<MovieTag> findParents(MovieTag movieTag, boolean recursive, Integer count);

    /**
     * 查找下级文章分类
     *
     * @param movieTag
     *            文章分类
     * @param recursive
     *            是否递归
     * @param count
     *            数量
     * @return 下级文章分类
     */
    List<MovieTag> findChildren(MovieTag movieTag, boolean recursive, Integer count);

}