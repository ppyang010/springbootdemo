package com.code.test;

/**
 * Created by Administrator on 2017/10/19.
 */
public class A {
    String name;
    public A(){
        System.out.println(1);
    }
    public A (String name){
        System.out.println(2);
        this.name=name;
    }

    public static void main(String[] args) {
        String s;
//        new B("hello");
    }

}
class B extends A{
    A a;
    public B(){
        System.out.println(4);
    }

    public B(String name){
        System.out.println(3);
        this.name=name;
        a=new A(name+"aaa");

    }



}

class NULL{
    public static void haha(){
        System.out.println("haha");
    }

    public static void main(String[] args) {
        ((NULL)null).haha();
    }

}



