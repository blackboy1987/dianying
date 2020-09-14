
package com.bootx.miniprogram.dao;

import com.bootx.dao.BaseDao;
import com.bootx.miniprogram.entity.App;

/**
 * Dao - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
public interface AppDao extends BaseDao<App, Long> {

    App findByCodeAndSecret(String code,String secret);

}