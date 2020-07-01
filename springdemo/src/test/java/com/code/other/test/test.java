package com.code.other.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/10/19.
 */
public abstract class test {

    public int constInt = 5;

    public void method() {

    }

    public abstract void method(int a);

    public static void main(String[] args) {
//        CountDownLatch c=new CountDownLatch(5);
//        Semaphore semaphore=new Semaphore(5);
//        String s = "1597,13386454";
//        StringTokenizer st = new StringTokenizer(s, ",");
//        Integer[] objs = new Integer[st.countTokens()];
//        int var3 = 0;
//
//        while(st.hasMoreTokens()) {
//            try {
//                objs[var3++] = Integer.parseInt(st.nextToken());
//            } catch (Throwable var5) {
//                ;
//            }
//        }
//        System.out.println(objs.length);

//        RestTemplate restTemplate = new RestTemplate();
//        String str=restTemplate.getForObject("https://mall.dxy.net/japi/platform/111020026",String.class);
//        System.out.println(str);

        //List of names
        List<String> names = Arrays.asList(new String[]{
                "barry",
                "andy",
                "ben",
                "chris",
                "bill"
        });
        //map and filter are piped and the stream is stored
        Stream<String> namesStream = names.stream()
                .map(n -> {
                    System.out.println("In map - " + n);
                    return n.toUpperCase();
                })
                .filter(upperName -> {
                    System.out.println("In filter - " + upperName);
                    return upperName.startsWith("B");
                }).limit(1);
        namesStream.collect(Collectors.toList());
    }
}
