package com.code;

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
