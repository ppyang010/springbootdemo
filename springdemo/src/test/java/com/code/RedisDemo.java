package com.code;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ccy
 * @description
 * @time 2018/10/30 下午3:40
 */
public class RedisDemo {
    public static void main(String[] args) {
//        JedisCluster
//        jedisSentinels();

        jedisDemo();
    }

    public static void jedisSentinels() {
        // 定义sentinel set
        Set<String> sentinels = new HashSet<String>();
        HostAndPort hp1 = new HostAndPort("192.168.202.119", 6300);
        String sentinel1 = hp1.toString();
        sentinels.add(sentinel1);
        HostAndPort hp2 = new HostAndPort("192.168.202.119", 6301);
        String sentinel2 = hp2.toString();
        sentinels.add(sentinel2);
        HostAndPort hp3 = new HostAndPort("192.168.202.119", 6302);
        String sentinel3 = hp3.toString();
        sentinels.add(sentinel3);
        JedisSentinelPool mymaster = new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = mymaster.getResource();
        jedis.set("jedis", "jedis");
        jedis.close();
    }

    public static void jedisDemo() {

        Jedis jedis = new Jedis("192.168.190.103", 6379);
        jedis.set("jedisDemo1", "jedisDemo1");
        System.out.println(jedis.get("jedisDemo1"));
        jedis.close();
    }
}
