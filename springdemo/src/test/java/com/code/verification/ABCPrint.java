package com.code.verification;

public class ABCPrint implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    public ABCPrint(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count>0){
            synchronized (prev){
                synchronized (self){
                    System.out.println(name);
                    self.notify();
                    count--;
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ABCPrint a1 = new ABCPrint("a", c, a);
        ABCPrint a2 = new ABCPrint("b", a, b);
        ABCPrint a3 = new ABCPrint("c", b, c);
        new Thread(a1).start();
        Thread.sleep(10);
        new Thread(a2).start();
        Thread.sleep(10);
        new Thread(a3).start();

    }
}
