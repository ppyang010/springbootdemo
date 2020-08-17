package com.code;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Test {
    public static void main(String[] args) {
//        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        Lock readLock = readWriteLock.readLock();
//        Lock writeLock = readWriteLock.writeLock();
//        Condition condition = writeLock.newCondition();
//        condition.signal();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//        cyclicBarrier.await();
//        Phaser phaser = new Phaser(10);
////        phaser
//        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(10);
//        PriorityQueue<Object> objects = new PriorityQueue<>();
//        new DelayQueue<>();
//        CopyOnWriteArrayList<Object> objects1 = new CopyOnWriteArrayList<>();
//        objects1.get(1);
//        objects1.add(1);
//
//        ConcurrentLinkedQueue<Object> ConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();
////        ConcurrentLinkedQueue.add()
//        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
////        concurrentSkipListMap.put();
////        CompletableFuture.supplyAsync(()->1+1).thenApplyAsync();
//        new ThreadPoolExecutor(1,2,30,TimeUnit.SECONDS,new ArrayBlockingQueue(10));
//        new ArrayList<>().add(1);
//        new Hashtable<>().put(null,null);
//        new HashMap<>().put(null,null);
        Object put = new ConcurrentHashMap<>().put(1, 1);
//        Collections.sy
        System.out.println(1);
//        int [][]a = new int[][]{{1,2,3},{8,9,10}};
//        System.out.println(Arrays.toString(a[1]));
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
//        objects.iterator()
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.signalAll();;
//        condition.await();
        lock.lock();
    }

}
