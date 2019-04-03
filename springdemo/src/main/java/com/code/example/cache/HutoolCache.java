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
    private String name;
    /**
     * 实际存储
     */
    private TimedCache<Object, Object> store;

    /**
     * 单位毫秒
     */
    private static final long DEFAULT_TIMEOUT = 60 * 1000;

    public HutoolCache(String name) {
        super(true);
        this.name = name;
        this.store = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);
    }


    public HutoolCache(String name, long timeout) {
        super(true);
        this.name = name;
        this.store = CacheUtil.newTimedCache(timeout);
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
        return this.store.get(key);
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
