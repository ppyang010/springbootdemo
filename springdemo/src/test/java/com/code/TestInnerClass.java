package com.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestInnerClass {
    static {
        System.out.println("TestInnerClass  Static");
    }

    public TestInnerClass() {
        System.out.println("TestInnerClass");
    }

    class InnerClass {
        public InnerClass() {
            System.out.println("InnerClass");
        }
    }

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new TestInnerClass();
        new Thread(new Runnable() {
            private static final int x=2;

            @Override
            public void run() {
                System.out.println(x);
            }
        });
        class mainInner{
            private static final int x=2;                         
            public void function(){
                System.out.println("mainInner");
            }
        }
//        System.out.println(test());
//        Integer a =100;
//        Integer b = 28;
//        Integer c = 128;
//        System.out.println(c == (a+b));
//        func2();
    }

    private static int test() {
        int x = 0;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            return 0;
        } finally {
            x = 2;
            System.out.println("run");
        }
//        new StringBuffer();
//        new StringBuilder();
//        new String();
//        new Integer(1);
//        new Boolean(true);

    }
    private static void func1(){
        new ArrayList<>();
        new LinkedList<>();
        new CopyOnWriteArrayList<>();
        new HashMap<>();
    }

    private static void func2(){
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null, null);
    }


}
