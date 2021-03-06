
package com.bootx.app.chengyu.service.impl;

import com.bootx.app.chengyu.dao.IdiomLevleDao;
import com.bootx.app.chengyu.entity.IdiomLevel;
import com.bootx.app.chengyu.service.IdiomLevelService;
import com.bootx.service.RedisService;
import com.bootx.service.impl.BaseServiceImpl;
import com.bootx.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class IdiomLevelServiceImpl extends BaseServiceImpl<IdiomLevel, Long> implements IdiomLevelService {

    @Resource
    private IdiomLevleDao idiomLevleDao;

    @Resource
    private RedisService redisService;

    @Override
    public IdiomLevel findByLevel(Integer level) {
        return idiomLevleDao.find("level",level);
    }

    @Override
    public boolean exist(String word) {
        return idiomLevleDao.exists("word",word);
    }

    @Override
    public void addCache(IdiomLevel idiomLevel) {
        flushCache(idiomLevel);
    }

    @Override
    public Map<String, Object> get(int i) {
        String s = redisService.get("idiom_" + i);
        if(StringUtils.isBlank(s)){
            IdiomLevel byLevel = findByLevel(i);
            flushCache(byLevel);;
        }
        s = redisService.get("idiom_" + i);
        return JsonUtils.toObject(s, new TypeReference<Map<String, Object>>() {
        });
    }

    @Override
    public IdiomLevel save(IdiomLevel entity) {
        entity = super.save(entity);
        flushCache(entity);
        return entity;
    }

    @Override
    public IdiomLevel update(IdiomLevel entity) {
        flushCache(entity);
        return super.update(entity);
    }

    @Override
    public IdiomLevel update(IdiomLevel entity, String... ignoreProperties) {
        flushCache(entity);
        return super.update(entity, ignoreProperties);
    }

    @Override
    public void delete(Long aLong) {
        removeCache(aLong);
        super.delete(aLong);
    }

    @Override
    public void delete(Long... longs) {
        for (Long id:longs) {
            removeCache(id);
        }
        super.delete(longs);
    }

    @Override
    public void delete(IdiomLevel entity) {
        removeCache(entity.getId());
        super.delete(entity);
    }


    private void flushCache(IdiomLevel idiomLevel){
        Map<String,Object> map = new HashMap<>();
        map.put("level",idiomLevel.getLevel());
        map.put("position",idiomLevel.getPosition());
        map.put("answers", idiomLevel.getAnswers());
        char[] chars = idiomLevel.getWord().toCharArray();
        List<String> words = new ArrayList<>();
        for (char c:chars) {
            words.add(c+"");
        }
        map.put("words", words);
        redisService.set("idiom_"+idiomLevel.getLevel(), JsonUtils.toJson(map));
    }

    private void removeCache(Long id){
        redisService.remove("idiom_"+id);
    }
}