package com.code.support;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;

/**
 * @author ccy
 * @description
 * @time 2019/4/2 下午4:17
 */
public class HutoolCacheManager extends AbstractTransactionSupportingCacheManager {
    @Override
    protected Collection<? extends Cache> loadCaches() {
        return null;
    }
}
