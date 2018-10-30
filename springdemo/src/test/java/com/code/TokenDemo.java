package com.code;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class TokenDemo {
    private int qps;
    private int countOfReq;
    private RateLimiter rateLimiter;

    public TokenDemo(int qps, int countOfReq) {
        this.qps = qps;
        this.countOfReq = countOfReq;
    }

    public TokenDemo processWithTokenBucket() {
        rateLimiter = RateLimiter.create(qps);
        return this;
    }

    public TokenDemo processWithLeakyBucket() {
        rateLimiter = RateLimiter.create(qps, 100, TimeUnit.MILLISECONDS);
        return this;
    }

    private void processRequest() {
        System.out.println("RateLimiter:" + rateLimiter.getClass());
        long start = System.currentTimeMillis();
        for (int i = 0; i < countOfReq; i++) {
            rateLimiter.acquire();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("处理请求数量:" + countOfReq + "," + "耗时：" + end + "," + "qps:" + rateLimiter.getRate() + "," + "实际qps：" + Math.ceil(countOfReq / (end / 1000.00)));
    }

    public void doProcess() throws InterruptedException {
        for (int i = 0; i < 20; i = i + 5) {
            TimeUnit.SECONDS.sleep(i);
            processRequest();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TokenDemo(50, 100).processWithTokenBucket().doProcess();
        new TokenDemo(50, 100).processWithLeakyBucket().doProcess();
    }
}
