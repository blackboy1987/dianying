package com.bootx.util;

import com.bootx.common.CommonAttributes;
import com.bootx.common.EnumConverter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean2;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EhCacheUtils {

    /**
     * CacheManager
     */
    private static final CacheManager CACHE_MANAGER = CacheManager.create();

    /**
     * BeanUtilsBean
     */
    private static final BeanUtilsBean BEAN_UTILS;

    private static final Object locker = new Object();


    static {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean2() {

            @Override
            public Converter lookup(Class<?> clazz) {
                Converter converter = super.lookup(clazz);
                if (converter != null) {
                    return converter;
                }

                if (clazz.isEnum()) {
                    EnumConverter enumConverter = new EnumConverter(clazz);
                    super.register(enumConverter, clazz);
                    return enumConverter;
                }
                if (clazz.isArray()) {
                    Converter componentConverter = lookup(clazz.getComponentType());
                    if (componentConverter != null) {
                        ArrayConverter arrayConverter = new ArrayConverter(clazz, componentConverter, 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, clazz);
                        return arrayConverter;
                    }
                }
                return super.lookup(clazz);
            }

        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtilsBean.register(dateConverter, Date.class);

        BEAN_UTILS = new BeanUtilsBean(convertUtilsBean);
    }

    /**
     * 不可实例化
     */
    private EhCacheUtils() {
    }

    /**
     * 获取缓存的数据
     * @param cacheName
     * @param cacheKey
     * @return
     */
    public static Object getCacheValue(String cacheName,String cacheKey) {
        Ehcache cache = CACHE_MANAGER.getEhcache(cacheName);
        Element cacheElement = cache.get(cacheKey);
        return cacheElement.getObjectValue();
    }

    /**
     * 获取缓存的数据
     * @param cacheName
     * @return
     */
    public static Map<Object,Object> getCacheValue(String cacheName) {
        Map<Object,Object> data = new HashMap<>();
        Ehcache cache = CACHE_MANAGER.getEhcache(cacheName);
        List keys = cache.getKeys();
        for (Object key:keys) {
            data.put(key,cache.get(key).getObjectValue());
        }
        return data;
    }

    /**
     * 获取缓存的数据
     * @return
     */
    public static Map<String,Map<Object,Object>> getCacheValue() {
        Map<String,Map<Object,Object>> map = new HashMap<>();
        String[] cacheNames = CACHE_MANAGER.getCacheNames();
        for (String cacheName:cacheNames){
            Map<Object,Object> data = new HashMap<>();
            Ehcache cache = CACHE_MANAGER.getEhcache(cacheName);
            List keys = cache.getKeys();
            for (Object key:keys) {
                data.put(key,cache.get(key).getObjectValue());
            }
            map.put(cacheName,data);
        }
        return map;
    }

    private static Cache getOrAddCache(String cacheName) {
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        if (cache == null) {
            synchronized (locker) {
                cache = CACHE_MANAGER.getCache(cacheName);
                if (cache == null) {
                    CACHE_MANAGER.addCacheIfAbsent(cacheName);
                    cache = CACHE_MANAGER.getCache(cacheName);
                }
            }
        }


        return cache;
    }


    /**
     * 设置缓存的数据
     * @param cacheName
     * @param cacheKey
     * @return
     */
    public static void setCacheValue(String cacheName,String cacheKey,Object value) {
        Ehcache cache = getOrAddCache(cacheName);

        cache.put(new Element(cacheKey,value));
    }

    /**
     * 清除缓存的数据
     * @param cacheName
     * @param cacheKey
     * @return
     */
    public static boolean removeCache(String cacheName,String cacheKey) {
        Ehcache cache = CACHE_MANAGER.getEhcache(cacheName);
        return cache.remove(cacheKey);
    }

    /**
     * 清除缓存的数据
     * @param cacheName
     * @return
     */
    public static void removeCache(String cacheName) {
        CACHE_MANAGER.removeCache(cacheName);
    }

    /**
     * 清除全部的数据
     * @return
     */
    public static void removeCache() {
        CACHE_MANAGER.clearAll();
    }
}
