package com.code;


import sun.security.util.AuthResources_it;

public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws
            InterruptedException {
        String a="a";
        String b="b";
        Thread threadA = new Thread(new lockA(a, b));
        threadA.start();
        Thread threadB = new Thread(new lockB(a, b));
        threadB.start();
        threadB.interrupt();
        System.out.println("interrupt");
    }


    static class lockA implements Runnable{

        Object a;
        Object b;

        public lockA(Object a,Object b){
            this.a=a;
            this.b=b;
        }

        @Override
        public void run() {
            //请求对象锁A
            System.out.println("lockA 请求锁a");
            synchronized (a){
                System.out.println("lockA 获取锁a");
                System.out.println("sleep");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("lockA 释放锁a");

        }

    }

    static class lockB implements Runnable {

        Object a;
        Object b;

        public lockB(Object a, Object b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            //请求对象锁A
            System.out.println("lockB 请求锁a");
            synchronized (a) {
                System.out.println("lockB 请求锁B");
            }

        }
    }
}