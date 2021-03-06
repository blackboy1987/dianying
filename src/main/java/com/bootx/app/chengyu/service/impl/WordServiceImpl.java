
package com.bootx.app.chengyu.service.impl;

import com.bootx.app.chengyu.dao.WordDao;
import com.bootx.app.chengyu.entity.Word;
import com.bootx.app.chengyu.service.WordService;
import com.bootx.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class WordServiceImpl extends BaseServiceImpl<Word, Long> implements WordService {

    @Resource
    private WordDao wordDao;

    @Override
    public boolean exist(String word) {
        return wordDao.exists("word",word);
    }
}