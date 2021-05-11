package com.code.syn;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestA {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(()->{});

        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            log.info("task1");
        },1000,2000, TimeUnit.MILLISECONDS);
//
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
//            log.info("task2");
//        },1000,2000, TimeUnit.MILLISECONDS);

        //运行状态保存在int值的高3位 (所有数值左移29位)
        int COUNT_BITS = Integer.SIZE - 3;
        int RUNNING = -1 << COUNT_BITS;// 接收新任务,并执行队列中的任务
        int SHUTDOWN = 0 << COUNT_BITS;// 不接收新任务,但是执行队列中的任务
        int STOP = 1 << COUNT_BITS;// 不接收新任务,不执行队列中的任务,中断正在执行中的任务
        int TIDYING = 2 << COUNT_BITS; //所有的任务都已结束,线程数量为0,处于该状态的线程池即将调用terminated()方法
        int TERMINATED = 3 << COUNT_BITS;// terminated()方法执行完成

        log.info("RUNNING={}",RUNNING);
        log.info("SHUTDOWN={}",SHUTDOWN);
        log.info("STOP={}",STOP);
        log.info("TIDYING={}",TIDYING);
        log.info("TERMINATED={}",TERMINATED);


    }
}
