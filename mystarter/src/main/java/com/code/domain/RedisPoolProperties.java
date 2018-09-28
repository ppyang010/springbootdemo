package com.code.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jedis 连接池配置
 *
 * @author ccy
 */
@ConfigurationProperties(prefix = "redis.pool")
public class RedisPoolProperties {

    /**
     * 最大活动对象数
     */
    private Integer maxTotal;
    /**
     * 最大能够保持idel状态的对象数
     */
    private Integer maxIdle;
    /**
     * 最小能够保持idel状态的对象数
     */
    private Integer minIdle;
    /**
     * 当池内没有返回对象时，最大等待时间
     */
    private Integer maxWaitMillis;

    /**
     * 当调用borrow Object方法时，是否进行有效性检查
     */
    private Boolean testOnBorrow;
    /**
     * 当调用return Object方法时，是否进行有效性检查
     */
    private Boolean testOnReturn;
    /**
     * “空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
     */
    private Integer timeBetweenEvictionRunsMillis;
    /**
     * 向调用者输出“链接”对象时，是否检测它的空闲超时；
     */
    private Boolean testWhileIdle;
    /**
     * 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
     */
    private Integer numTestsPerEvictionRun;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public RedisPoolProperties setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
        return this;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public RedisPoolProperties setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
        return this;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public RedisPoolProperties setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public RedisPoolProperties setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
        return this;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public RedisPoolProperties setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        return this;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public RedisPoolProperties setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
        return this;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public RedisPoolProperties setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        return this;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public RedisPoolProperties setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public RedisPoolProperties setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
        return this;
    }

    @Override
    public String toString() {
        return "RedisPoolProperties{" +
                "maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", maxWaitMillis=" + maxWaitMillis +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", testWhileIdle=" + testWhileIdle +
                ", numTestsPerEvictionRun=" + numTestsPerEvictionRun +
                '}';
    }
}
