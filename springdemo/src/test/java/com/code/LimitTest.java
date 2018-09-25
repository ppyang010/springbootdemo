package com.code;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

/**
 * 限流测试
 */
public class LimitTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100D/60);
        System.out.println(new Date());
        for(int i= 0; i<100;i++){
            System.out.println(rateLimiter.acquire());
        }
        System.out.println(new Date());
    }
}
