package com.code.other.test;

/**
 * Created by Administrator on 2017/10/19.
 * 简单的死锁
 */
public class lock {

    public static void main(String[] args) {
        String a="a";
        String b="b";
        Thread threadA = new Thread(new lockA(a, b));
        threadA.start();
        Thread threadB = new Thread(new lockB(a, b));
        threadB.start();

    }
}
class lockA implements Runnable{

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lockA 请求锁b");
            synchronized (b){
                System.out.println("lockA 获取锁b");
            }
        }
        }

}

class lockB implements Runnable{

    Object a;
    Object b;

    public lockB(Object a,Object b){
        this.a=a;
        this.b=b;
    }

    @Override
    public void run() {
        //请求对象锁A
        System.out.println("lockB 请求锁b");
        synchronized (b){
            System.out.println("lockB 获取锁b");
            System.out.println("sleep");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lockB 请求锁a");
            synchronized (a){
                System.out.println("lockB 获取锁a");
            }
        }

    }
}
