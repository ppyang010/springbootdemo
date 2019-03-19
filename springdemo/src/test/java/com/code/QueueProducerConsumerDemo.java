package com.code;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ccy
 */
public class QueueProducerConsumerDemo {


    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue), "consumer-1").start();
        new Thread(new Consumer(queue), "consumer-2").start();

    }


    public static class Producer implements Runnable {
        private BlockingQueue queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(String.format("第%d次put", i + 1));
                    queue.put("msg-" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class Consumer implements Runnable {
        private BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            try {
                String get;
                while ((get = (String) queue.take()) != null) {

                    System.out.println(String.format("线程%s读取msg=%s", Thread.currentThread().getName(), get));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
