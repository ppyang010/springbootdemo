package com.code.syn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo {
    static Map<String,Object> cacheMap=new HashMap<>();
    static ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    static ReentrantLock lock =new ReentrantLock();
    static Lock read=rwl.readLock();
    static Lock write=rwl.writeLock();
    public static final Object get(String key) throws InterruptedException {
        lock.lock();
        lock.unlock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
        System.out.println("开始读取数据");
        read.lock(); //读锁
        try {
            return cacheMap.get(key);
        }finally {
            read.unlock();
        }
    }
    public static final Object put(String key,Object value){
        write.lock();
        System.out.println("开始写数据");
        try{
            return cacheMap.put(key,value);
        }finally {
            write.unlock();
        }
    }

    public static void main(String[] args) {
        //低位演示
        int low =11&16;
        System.out.println(low);
        //低位演示扩容后的index
        int lowIndex =11&(16-1);
        System.out.println(lowIndex);
        lowIndex =11&(32-1);
        System.out.println(lowIndex);
        System.out.println("------------");
        //高位演示
        int hight =9912&16;
        System.out.println(hight);
        //高位演示扩容后的index
        int hightIndex =9912&(16-1);
        System.out.println(hightIndex);
        hightIndex =9912&(32-1);
        System.out.println(hightIndex);
    }
}