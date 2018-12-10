package com.code;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限流测试
 */
public class LimitTest {

    public static void main(String[] args) throws InterruptedException {
//        limitMethodA();
//        limitMethodB();
//        limitMethodC();
        limitMethodD();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }

    private static void limitMethodA() {
        RateLimiter rateLimiter = RateLimiter.create(100D/60);
        System.out.println(new Date());
        for(int i= 0; i<100;i++){
            System.out.println(rateLimiter.acquire());
        }
        System.out.println(new Date());
    }

    private static void limitMethodB() throws InterruptedException {
        System.out.println(TimeUnit.SECONDS.toMicros(1L));
        System.out.println(System.currentTimeMillis()/1000);
        RateLimiter rateLimiter = RateLimiter.create(100);
        while (true){
            System.out.println(System.currentTimeMillis()/1000);
            System.out.println(rateLimiter.acquire(100));
        }

    }

    private static void limitMethodC() throws InterruptedException {
        System.out.println(System.currentTimeMillis()/1000);
        RateLimiter rateLimiter = RateLimiter.create(100,20,TimeUnit.SECONDS);
        int get = 100;
        while (true){
            System.out.println(System.currentTimeMillis()/1000);
            System.out.println("get="+get);
            System.out.println(rateLimiter.acquire(get));
//            get++;
        }


    }


    private static void limitMethodD() throws InterruptedException {
        System.out.println(TimeUnit.SECONDS.toMicros(1L));
        System.out.println(System.currentTimeMillis()/1000);
        RateLimiter rateLimiter = RateLimiter.create(1);
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(rateLimiter.acquire(10));
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(rateLimiter.acquire(1));
        System.out.println(System.currentTimeMillis()/1000);


    }

}
