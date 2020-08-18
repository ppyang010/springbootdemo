package com.code;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import com.google.common.collect.Lists;

import java.util.concurrent.Phaser;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws InterruptedException {
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
//        new CyclicBarrier();
//        phaserDemo1();
//        phaserDemo2();
        phaserDemo3();
//        Lists.newArrayList().parallelStream()

    }

    public static void phaserDemo1() {

        //CyclicBarrier
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "-到达等待点1 phase=" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "-done1 phase=" + phaser.getPhase());

                System.out.println(Thread.currentThread().getName() + "-到达等待点2 phase=" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "-done2 phase=" + phaser.getPhase());


            }, "Phaser-Demo1-Thread-" + i).start();
        }


        return;
    }


    public static void phaserDemo2() throws InterruptedException {

        //CyclicBarrier
        Phaser phaser = new Phaser(5);
        System.out.println((Thread.currentThread().getName() + "-wait phase=" + phaser.getPhase()));
        phaser.awaitAdvance(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-done phase=" + phaser.getPhase());
                phaser.arrive();

            }, "Phaser-Demo2-Thread-" + i).start();
        }
        Thread.sleep(1);
        System.out.println(Thread.currentThread().getName() + "-done phase=" + phaser.getPhase());
        return;
    }

    public static void phaserDemo3() {

        //通过Phaser控制任务的执行轮数
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (!phaser.isTerminated() && phaser.getPhase() < 3) { //只要Phaser没有终止, 各个线程的任务就会一直执行 只跑3
//                    System.out.println(Thread.currentThread().getName() + "-到达等待点 phase=" + phaser.getPhase());
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(Thread.currentThread().getName() + "-done phase=" + phaser.getPhase());
                }
            }, "Phaser-Demo3-Thread-" + i).start();
        }


        return;
    }

}
