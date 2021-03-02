package com.bootx.util;


import com.bootx.util.wechat.AccessToken;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhCacheUtils {
    private static CacheManager accessTokenCacheManager;

    static {
        accessTokenCacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("accessToken", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, AccessToken.class, ResourcePoolsBuilder.heap(10))).build();
        accessTokenCacheManager.init();
    }

    public static AccessToken getAccessToken(String key){
        Cache<String, AccessToken> accessTokenCache = accessTokenCacheManager.getCache("accessToken", String.class, AccessToken.class);
        AccessToken accessToken = accessTokenCache.get(key);
        if(accessToken!=null){
            if((accessToken.getCreateDate()+(accessToken.getExpiresIn()-120)* 1000L)>System.currentTimeMillis()){
                return accessToken;
            }
            return null;
        }
        return null;
    }

    public static void setAccessToken(String key, AccessToken accessToken){
        Cache<String, AccessToken> accessTokenCache = accessTokenCacheManager.getCache("accessToken", String.class, AccessToken.class);
        accessTokenCache.put(key,accessToken);
    }

    public static void removeAccessToken(String key){
        Cache<String, AccessToken> accessTokenCache = accessTokenCacheManager.getCache("accessToken", String.class, AccessToken.class);
        accessTokenCache.remove(key);
    }

}
