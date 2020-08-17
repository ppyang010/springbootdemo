package com.code;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Thread putThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("put thread start i=" + finalI);
                    try {
                        queue.put(finalI);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("put thread end i=" + finalI);
                }
            });
            putThread.start();
        }


        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    for (int time = 0; time < 3; time++) {
                        System.out.println("take from putThread: " + queue.take() + "  take times=" + time);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
                System.out.println("take thread end");
            }
        });

        Thread.sleep(1000);
        takeThread.start();
    }
}
