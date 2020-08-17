package com.code;

import java.util.concurrent.*;

public class ForkJoinPoolDemo {

    /**
     * 1到n个数的和
     */
    public static class SumTask extends RecursiveTask<Long> {

        private static final int threshold = 10;
        private long start;
        private long end;

        public SumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {
            long sum = 0;
            //如果小于阈值直接相加
            if (end - start <= threshold) {
                for (long l = start; l <= end; l++) {
                    sum += l;
                }
            } else {
                long middle = (start + end) / 2;
                SumTask taskLeft = new SumTask(start, middle);
                SumTask taskRight = new SumTask(middle + 1, end);
                taskLeft.fork();
                taskRight.fork();
                sum = taskLeft.join() + taskRight.join();
            }


            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask sumTask = new SumTask(1L, 100L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(sumTask);
        Long r = submit.get();
        System.out.println(r);
        System.out.println(forkJoinPool.getPoolSize());
        forkJoinPool.shutdown();

    }
}
