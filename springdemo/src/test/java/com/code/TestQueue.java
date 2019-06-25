package com.code;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author ccy
 * @description
 * @time 2019/6/25 下午5:40
 */
public class TestQueue {


    public static void main(String[] args) throws InterruptedException {
        //SynchronousQueue使用实例
        //https://www.jianshu.com/p/b7f7eb2bc778
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.take());
        System.out.println(queue.size());
    }
}
