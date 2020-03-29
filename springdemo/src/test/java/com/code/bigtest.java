package com.code;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ccy
 * @description
 * @time 2018/11/14 下午12:08
 */
public class bigtest {
    public static void main(String[] args) throws InterruptedException {
//        bigtest();
//        int i = 1;
//        i++;
//        i = i++;
//        int j = i++;//1
//        int k = i + ++i * i++;
//        System.out.println(i);
//        System.out.println(j);
//        System.out.println(k);
//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        countDownLatch.await();
//        for (int x = 0, index = 0; x < 10; x++) {
//            index += 2;
//            System.out.println(index);
//        }
//        new People();

//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.putIfAbsent();
//        concurrentHashMap.computeIfAbsent()
//        LongAdder longAdder = new LongAdder();
//        longAdder.increment();


        BigDecimal lilv = new BigDecimal("1.028");
        int year = 3;
        BigDecimal count = lilv;
        for (int i = 0; i < year; i++) {
            count = count.multiply(lilv);
        }

        BigDecimal money = new BigDecimal("10000");
        BigDecimal res = money.multiply(count);
        System.out.println(res.toPlainString());
    }

    public static void bigtest() throws InterruptedException {
        BigDecimal bigDecimal = new BigDecimal("1.32").setScale(1, BigDecimal.ROUND_UP);
        System.out.println(bigDecimal.toString());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.await();
        countDownLatch.countDown();
        Semaphore semaphore = new Semaphore(10);
        semaphore.tryAcquire(1000, TimeUnit.MILLISECONDS);
        semaphore.acquire(1);
        semaphore.release(1);
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.incrementAndGet();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {

        });
        executorService.execute(() -> {

        });

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        reentrantLock.lock();
        reentrantLock.tryLock();
        reentrantLock.tryLock(1000, TimeUnit.SECONDS);
        reentrantLock.unlock();
        condition.await();
        condition.signal();
        condition.signalAll();

        ArrayList<Object> list = new ArrayList<>();
    }
}
