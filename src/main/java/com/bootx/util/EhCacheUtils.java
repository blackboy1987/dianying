package com.bootx.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.HashMap;
import java.util.Map;

public class EhCacheUtils {
    private static final CacheManager cacheManager = CacheManager.create();
    private static final Cache siteInfoCache = cacheManager.getCache("siteInfo");
    private static final Cache idiomListCache = cacheManager.getCache("idiomList");
    private static final Cache noticeListCache = cacheManager.getCache("noticeList");




    public static Object getNoticeListInfo(String key){
        if(noticeListCache!=null){
            Element element = noticeListCache.get("noticeList_"+key);
            if(element==null){
                return null;
            }else{
                Object result = element.getObjectValue();
                if(result==null){
                    return null;
                }
                return result;
            }
        }else{
            return null;
        }
    }

    public static void setCacheNoticeList(Map<String,Object> map){
        if(noticeListCache!=null&&map!=null&&map.keySet().size()>0){
            Map<String,Object> result = new HashMap<>();
            idiomListCache.put(new Element("noticeList_"+map.get("key"),map));
        }
    }

    public static void removeCacheNoticeList(String key){
        if(idiomListCache!=null){
            idiomListCache.remove("noticeList_"+key);
        }
    }

}
