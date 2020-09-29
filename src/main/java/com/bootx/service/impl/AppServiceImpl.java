
package com.bootx.service.impl;

import com.bootx.dao.AppDao;
import com.bootx.entity.App;
import com.bootx.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<App, Long> implements AppService {

    @Autowired
    private AppDao appDao;

    @Override
    public App findByAppCode(String appCode) {
        return appDao.find("appCode",appCode);
    }

    @Override
    public boolean exist(String appCode, String appSecret) {
        App app = findByAppCode(appCode);
        if(app==null){
            return false;
        }
        if(!StringUtils.equals(appSecret, app.getToken())){
            return false;
        }


        return true;
    }
}