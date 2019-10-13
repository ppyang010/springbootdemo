package com.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ccy
 * @description
 * @time 2019-09-27 11:16
 */
@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            int i = 1 / 0;
            System.out.println(Thread.currentThread().getName());
            return 100;
        });
        //CompletionException
        future.join();
        //ExecutionException
//        future.get();
    }

    @Test
    public void test03() throws InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        CompletableFuture<String> cf1 = cf.whenComplete((s, throwable) -> {
            if (throwable == null) {
                log.info(s);
            }
        });
        Thread.sleep(2000);
        log.info(cf.join());
        log.info(cf1.join());
    }

}
