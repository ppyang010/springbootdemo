package com.code.example.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author ccy
 * @description
 * @time 2019/4/2 下午4:17
 */
public class HutoolCacheManager extends AbstractTransactionSupportingCacheManager {

    private Collection<? extends Cache> hutoolCaches = Collections.emptySet();
    private HutoolCacheProperties hutoolCacheProperties;

    public HutoolCacheManager(HutoolCacheProperties hutoolCacheProperties) {
        this.hutoolCacheProperties = hutoolCacheProperties;
    }

    public HutoolCacheManager() {
    }

    public HutoolCacheManager setHutoolCaches(Collection<? extends Cache> hutoolCaches) {
        this.hutoolCaches = hutoolCaches;
        return this;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return hutoolCaches;
    }


    /**
     * 根据name获取cache 不存在时 新建一个这个name的cache
     */
    @Nullable
    @Override
    protected Cache getMissingCache(String name) {
        return new HutoolCache(name, hutoolCacheProperties);
    }



    public static void main(String[] args) {
        TimedCache<String, String> objects = CacheUtil.newTimedCache(1000);
        objects.get("x", false);
    }

}
