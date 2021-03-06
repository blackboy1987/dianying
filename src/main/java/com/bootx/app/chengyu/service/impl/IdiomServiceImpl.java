
package com.bootx.app.chengyu.service.impl;

import com.bootx.app.chengyu.dao.IdiomDao;
import com.bootx.app.chengyu.entity.Idiom;
import com.bootx.app.chengyu.service.IdiomService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class IdiomServiceImpl extends BaseServiceImpl<Idiom, Long> implements IdiomService {

    @Autowired
    private IdiomDao idiomDao;

    @Override
    public boolean exist(String fullName) {
        return idiomDao.exists("fullName",fullName);
    }

    @Override
    public Idiom findByLevel(Integer level) {
        return idiomDao.find("level",level);
    }
}