package com.code.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ex {
    public static void main(String[] args) {
        ExecutorService executorService=new ThreadPoolExecutor(10,10,10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable> ());



    }
}
