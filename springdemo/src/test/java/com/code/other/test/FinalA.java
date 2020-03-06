package com.code.other.test;

import com.code.data.hello.People;

/**
 * @author ccy
 * @description
 * @time 2020-01-30 20:13
 */
public class FinalA {
    public final void get(){
    }

    public void getB(){
    }

    public static void main(String[] args) {

        People test = test();
        System.out.println(test.getName());

        People test2 = test2();
        System.out.println(test2.getName());

        System.out.println(test3());


    }

    public static People test() {
        People people;
        try{
            people = new People();
            people.setName("try");
            return people;
        }finally {
            people = new People();
            people.setName("finally");
        }
    }

    public static People test2() {
        People people=new People();;
        try{
            people.setName("try");
            return people;
        }finally {
            people.setName("finally");
        }
    }

    public static String test3() {
       String x ;
        try{
            x="try";
            return x;
        }finally {
            x="finally";
        }
    }
}
