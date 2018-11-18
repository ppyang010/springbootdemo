package com.code.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * redis lock
 *  实现参考
 *  http://www.wangtianyi.top/blog/2018/06/24/shou-ba-shou-jiao-ni-she-ji-bing-shi-xian-yi-ge-ji-yu-redisde-fen-bu-shi-suo/
 * @author ccy
 */
@Component
public class SimpleRedisLock {

    @Autowired
    private JedisPool jedisPool;

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_PREFIX = "SimpleRedisLock_";
    private static final String LOCK_MSG = "OK";

    private static final Long UNLOCK_MSG = 1L;
    /**
     * 默认超时时间
     */
    private static final int DEFAULT_EXPIRE_TIME = 60 * 1000;
    /**
     * 默认睡眠时间
     */
    private static final long DEFAULT_SLEEP_TIME = 100;


    public void lock(String key, String request, int timeout) {
        Jedis jedis = jedisPool.getResource();
        try {
            while (timeout >= 0) {
                String result = jedis.set(LOCK_PREFIX + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_EXPIRE_TIME);
                if (LOCK_MSG.equals(result)) {
                    jedis.close();
                    return;
                }
                Thread.sleep(DEFAULT_SLEEP_TIME);
                timeout -= DEFAULT_SLEEP_TIME;

            }
        } catch (InterruptedException e) {
            jedis.close();
            e.printStackTrace();
        }
    }


    public boolean unlock(String key, String request) {
        Jedis jedis = jedisPool.getResource();

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(LOCK_PREFIX + key), Collections.singletonList(request));

        jedis.close();
        return UNLOCK_MSG.equals(result);
    }
}
