
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.AppDao;
import com.bootx.entity.App;
import com.bootx.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
        StringBuffer sb = new StringBuffer("select id,createdDate,appId,username from app ");
        StringBuffer totalSql = new StringBuffer("select count(1) from app");

        if(StringUtils.isNotBlank(pageable.getOrderProperty())&&pageable.getOrderDirection()!=null){
            sb.append(" ORDER BY "+pageable.getOrderProperty()+" "+pageable.getOrderDirection().name());
        }else{
            sb.append(" ORDER BY createdDate desc");
        }
        sb.append(" LIMIT "+(pageable.getPageNumber()-1)*pageable.getPageSize()+","+pageable.getPageSize());

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
        Long total = jdbcTemplate.queryForObject(totalSql.toString(),Long.class);

        return new Page(list,total,pageable);
    }

    @Override
    public Map<String, Object> findJdbc(Long id) {
        return null;
    }
}