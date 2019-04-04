package com.code.example.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * spring cache 抽象 包装实际的存储
 *
 * @author ccy
 */
public class HutoolCache extends AbstractValueAdaptingCache {


    /**
     * 实际存储
     */
    private TimedCache<Object, Object> store;
    /**
     * cache name
     */
    private String name;

    /**
     * 单位毫秒
     */
    private long timeout = 60 * 1000;

    /**
     * true:如果用户在超时前调用了get(key)方法，会重头计算起始时间
     */
    private boolean isUpdateLastAccess = false;


    /**
     * 是否定期检查缓存是否过期
     */
    private boolean isSchedulePrune = true;

    /**
     * 检查缓存过期的时间间隔
     * 单位毫秒
     */
    private long schedulePruneTimeout = 30 * 1000;


    public HutoolCache(String name) {
        super(true);
        this.name = name;
        this.store = CacheUtil.newTimedCache(this.timeout);
    }

    public HutoolCache(String name, long timeout, boolean isUpdateLastAccess, boolean isSchedulePrune, long schedulePruneTimeout) {
        super(true);
        this.name = name;
        this.isUpdateLastAccess = isUpdateLastAccess;
        this.isSchedulePrune = isSchedulePrune;
        this.schedulePruneTimeout = schedulePruneTimeout;
        this.timeout = timeout;
        this.store = CacheUtil.newTimedCache(this.timeout);
        if (this.isSchedulePrune) {
            this.store.schedulePrune(this.schedulePruneTimeout);
        }
    }

    public HutoolCache(String name, HutoolCacheProperties properties) {
        super(true);
        this.name = name;
        this.isUpdateLastAccess = properties.isUpdateLastAccess();
        this.isSchedulePrune = properties.isSchedulePrune();
        this.schedulePruneTimeout = properties.getSchedulePruneTimeout();
        this.timeout = properties.getTimeout();
        this.store = CacheUtil.newTimedCache(this.timeout);
        if (this.isSchedulePrune) {
            this.store.schedulePrune(this.schedulePruneTimeout);
        }
    }

    /**
     * Create an {@code AbstractValueAdaptingCache} with the given setting.
     * <p>
     * allowNullValues whether to allow for {@code null} values
     */
    protected HutoolCache() {
        super(true);
    }


    /**
     * 从实际存储中获取值
     *
     * @return
     */
    @Nullable
    @Override
    protected Object lookup(Object key) {
        return this.store.get(key,this.isUpdateLastAccess);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.store;
    }


    @Override
    public void put(Object key, @Nullable Object value) {
        this.store.put(key, toStoreValue(value));
    }


    /**
     * <code>
     * Object existingValue = cache.get(key);
     * if (existingValue == null) {
     * cache.put(key, value);
     * return null;
     * } else {
     * return existingValue;
     * }
     * </code>
     *
     * @param key
     * @param value
     * @return
     */
    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        if (!this.store.containsKey(key)) {
            this.store.put(key, value);
            return null;
        } else {
            return toValueWrapper(this.store.get(key));
        }
    }

    @Override
    public void evict(Object key) {
        store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }


    /**
     * 这个暂时不实现
     *
     * @param key
     * @param valueLoader
     * @param <T>
     * @return
     */
    @Nullable
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }
}
