package com.code;

import cn.hutool.core.collection.IterUtil;
import com.google.common.collect.Lists;

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


//        BigDecimal lilv = new BigDecimal("1.028");
//        int year = 3;
//        BigDecimal count = lilv;
//        for (int i = 0; i < year; i++) {
//            count = count.multiply(lilv);
//        }
//
//        BigDecimal money = new BigDecimal("10000");
//        BigDecimal res = money.multiply(count);
//        System.out.println(res.toPlainString());

//        String str1 = "lv"+"test";
//        String st = "lv";
//        String str2 = st+"test";
//        System.out.println(str1==str2);
//        AtomicStampedReference;

        int amount201906 = 2541031;
        int amount201907 = 2593831;
        int amount201908 = 2646631;
        int amount201909 = 2699431;
        int amount201910 = 2752231;
        int amount201911 = 2805031;
        int amount201912 = 2857831;
        int amount202001 = 2910631;
        int amount202002 = 2963431;
        int amount202003 = 3016231;
        int amount202004 = 3069031;
        //2020-05
        int amount = 3121831;
        //每月交
        int amountMonth = 52800;
        //每月交 调整后
        int amountMonthPlus = 240000;
        int month = 12;


        ArrayList<Integer> months = Lists.newArrayList();
        months.add(amount201906);
        months.add(amount201907);
        months.add(amount201908);
        months.add(amount201909);
        months.add(amount201910);
        months.add(amount201911);
        months.add(amount201912);
        months.add(amount202001);
        months.add(amount202002);
        months.add(amount202003);
        months.add(amount202004);
        months.add(amount);

        //初始为202006提取
        //add=0
        int tiquMonthAdd = 6;
        int oldSaveMonth = 1;

        for (int add = 0; add < tiquMonthAdd; add++) {
            if (add < oldSaveMonth) {
                amount += amountMonth;
            } else {
                amount += amountMonthPlus;
            }
            months.add(amount);
            months.remove(IterUtil.getFirst(months));
        }


        Integer sum = months.stream().reduce(0, Integer::sum);
        int res = sum / month * 15;
        System.out.println(res);
        //425000
        //28314
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
