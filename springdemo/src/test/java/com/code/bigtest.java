package com.code;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ccy
 * @description
 * @time 2018/11/14 下午12:08
 */
public class bigtest {
    public static void main(String[] args) throws InterruptedException {
        BigDecimal bigDecimal = new BigDecimal("1.32").setScale(1, BigDecimal.ROUND_UP);
        System.out.println(bigDecimal.toString());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.await();
        countDownLatch.countDown();
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire(1);
        semaphore.release(1);
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.incrementAndGet();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{

        });

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition =reentrantLock.newCondition();

        reentrantLock.lock();
        reentrantLock.tryLock();
        reentrantLock.tryLock(1000,TimeUnit.SECONDS);
        reentrantLock.unlock();
        condition.await();
        condition.signal();
        condition.signalAll();

    }
}
