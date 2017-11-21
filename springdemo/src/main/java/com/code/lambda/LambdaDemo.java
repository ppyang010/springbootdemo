package com.code.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaDemo {
    public static void main(String[] args) {
        FunctionOne o1 = new FunctionOne() {
            @Override
            public void doWork(String str) {
                System.out.println(str);
            }
        };

        FunctionOne o2 =(str)-> System.out.println(str);
        o1.doWork("hello");
        Consumer<String> c=(str)-> System.out.println(str);
        c.accept("c1");

    }
}
