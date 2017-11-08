package com.code.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/10/19.
 */
public abstract class test {

    public int constInt=5;

    public void method(){

    }
    public abstract  void method(int a);

    public static void main(String[] args) {
        CountDownLatch c=new CountDownLatch(5);
        Semaphore semaphore=new Semaphore(5);

    }
}
