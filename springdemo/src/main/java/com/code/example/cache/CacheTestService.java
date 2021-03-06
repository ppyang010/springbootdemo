package com.code.example.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ccy
 */
@Service
public class CacheTestService {

    @Cacheable(value = "simpleCache1", cacheManager = "simpleCacheManager")
    public String simpleCache1(String param) {
        System.out.println("enter simpleCache1 method");
        return param;
    }

    @Cacheable(value = "hutoolCache1", cacheManager = "hutoolCacheManager")
    public String hutoolCache1(String param) {
        System.out.println("enter hutoolCache1 method");
        return param;
    }

    @Cacheable(value = "dontUseGlobalProperties", cacheManager = "hutoolCacheManager")
    public String dontUseGlobalProperties(String param) {
        System.out.println("enter dontUseGlobalProperties method");
        return param;
    }

    @CacheEvict("removeHutoolCache1")
    public void removeHutoolCache1(String param){

    }
}
