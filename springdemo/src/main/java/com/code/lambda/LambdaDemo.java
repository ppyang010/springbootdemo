package com.code.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class LambdaDemo {
    public static void main(String[] args) {
        fn2();
    }

    public void fn1(){
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

    public static void fn2(){
        Stream.of(1,2,3,4,5).forEach(System.out::println);
    }


    /**
     * reduce()
     */
    public static  void reduceOfStream(){
        List<Integer> lists = Arrays.asList(1, 2, 3, 3, 4, 5);
        // 元素的总和
        int sum = lists.stream().reduce(0, (x, y) -> x + y);
        Optional<Integer> sum2 = lists.stream().reduce(Integer::sum);
        System.out.println("sum = " + sum);
        System.out.println("sum2 = " + sum2.get());
        // 求最大值
        int max = lists.stream().reduce(0, (x, y) -> x > y ? x : y);
        Optional<Integer> max2 = lists.stream().reduce(Integer::max);
        System.out.println("max = " + max);
        System.out.println("max2 = " + max2.get());
        // 最小值
        int min = lists.stream().reduce(1, (x, y) -> x > y ? y : x);
        Optional<Integer> min2 = lists.stream().reduce(Integer::min);
        System.out.println("min = " + min);
        System.out.println("min2 = " + min2.get());
    }
}
