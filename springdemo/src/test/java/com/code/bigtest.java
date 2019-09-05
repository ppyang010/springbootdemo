package com.code;

import com.code.data.hello.People;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
        for (int x = 0, index = 0; x < 10; x++) {
            index += 2;
            System.out.println(index);
        }
        new People();

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
