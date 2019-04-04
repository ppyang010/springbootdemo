package com.code.example.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 实际的缓存框架hutool的缓存参数
 * 全局参数
 * @author ccy
 */
@Data
@ConfigurationProperties(prefix = "hutool.cache")
public class HutoolCacheProperties {

    /**
     * 缓存过期时间
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
}
