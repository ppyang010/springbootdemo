package com.code;

import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author ccy
 * @description
 * @time 2020-08-19 14:12
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Semaphore one = new Semaphore(0);
        Semaphore two = new Semaphore(0);
//        two.release();
//        two.acquire();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CountDownLatch countDownLatch = new CountDownLatch(10);
//        cyclicBarrier.await()
        HashSet<Object> objects = new HashSet<>();
        return super.loadClass(name);
    }
}
