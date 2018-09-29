package com.code.hello;

import com.code.lock.SimpleRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/10/3.
 */
@RestController
public class HelloController {
    public static Integer STOCK ;

    @Value("${myParam}")
    private String myParam;

    @Autowired
    private People people;
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SimpleRedisLock simpleRedisLock;

    @RequestMapping(value = {"hello", "hi"}, method = RequestMethod.GET)
    public String hello() {
        return "hello spring boot!!! MyParam = " + myParam;
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return people.getName();
    }

    @RequestMapping(value = "redis", method = RequestMethod.GET)
    public String redisTest(String val) {
        Jedis jedis = jedisPool.getResource();
        try {
            String msg = jedis.set("test", val);
            System.out.println(msg);
            return jedis.get("test");
        } finally {
            jedis.close();
        }
    }

    @RequestMapping(value = "noLock", method = RequestMethod.GET)
    public Integer redisLockTest() throws InterruptedException {
        reset();
        Thread.sleep(1);
        final int[] count = {0};
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {

                try {
                    buyStock(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            },"simpleLockThread-"+finalI).start();
        }
        Thread.sleep(5000);

        System.out.println(count[0]);
        return STOCK;
    }

    @RequestMapping(value = "reset", method = RequestMethod.GET)
    public String reset() {
        STOCK = 10;
        return STOCK.toString();
    }

    @RequestMapping(value = "simpleLock", method = RequestMethod.GET)
    public Integer simpleLock() throws InterruptedException {
        reset();
        Thread.sleep(1);
        final int[] count = {0};
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {

                try {
                    simpleRedisLock.lock("simpleLock", String.valueOf(finalI), 1000);
                    buyStock(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    simpleRedisLock.unlock("simpleLock", String.valueOf(finalI));
                }
            },"simpleLockThread-"+finalI).start();
        }
        Thread.sleep(5000);

        System.out.println(count[0]);
        return STOCK;
    }

    private void buyStock(int[] count) throws InterruptedException {
        int s = STOCK;
        Thread.sleep(1);
        if (s > 0) {
            STOCK = s - 1;
            count[0]++;
            System.out.println("购买成功");
        } else {
            System.out.println("购买失败");
        }
    }

}
