package com.code.other.test;

import java.util.Random;

public class C10 {

    public static void main(String[] args) {
        Random generator = new Random();
        final int ITERATIONS = 5;
        //数组大小  数据由随机数组成
        int[] N = new int[] {100000, 200000, 500000, 1000000, 2000000};

        for (int n : N) {
            int[] array = new int[n];
            long buildArrayStart = System.currentTimeMillis();
            for (int i = 0; i < array.length; i++) {
                //数组由随机数组成
                array[i] = generator.nextInt();
            }
            long buildArrayEnd = System.currentTimeMillis();
//            System.out.format("构建数组时间->%d - %d ms\n", array.length, buildArrayEnd-buildArrayStart);
            int q = generator.nextInt();

            float timing = 0;
            for (int it = 0; it < ITERATIONS; it++) {
                long start = System.currentTimeMillis();
                linearSearch(array, n, q);
                long end = System.currentTimeMillis();
                timing += end - start;
            }
//            timing = timing / (long)(ITERATIONS);
            timing /= (long)(ITERATIONS);

//            System.out.println(array.length + "," + timing);
            System.out.format("%d个随机数平均用时 - %f\n", array.length, timing);
            System.out.println("-------------------------");
            System.gc();
        }
    }

    /**
     *
     * @param a 遍历的数组 数组由随机数组成
     * @param n 循环次数 {100000, 200000, 500000, 1000000, 2000000};
     * @param q 找的值  generator.nextInt();
     * @return
     */
    public static int linearSearch(int[] a, int n, int q) {

        long start = System.currentTimeMillis();
        int index = 0;
        int position = -1;

        while (index < n && a[index] != q) {
            index += 1;
        }

        if (index == n) {

        } else {
            position = index;
        }

        long end = System.currentTimeMillis();
        System.out.println("timing : " + (end - start) + "ms");
        return position;

    }

}
