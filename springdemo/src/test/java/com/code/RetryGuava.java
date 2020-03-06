package com.code;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ccy
 * @description
 * @time 2020-02-28 14:42
 */
@Slf4j
public class RetryGuava {
    public static void main(String[] args) throws ExecutionException, RetryException {
//        test();

        List<List<String>> col = Lists.newArrayList();
        ArrayList<String> str1 = Lists.newArrayList();
        str1.add("str1");
        ArrayList<String> str2 = Lists.newArrayList();
        str2.add("str2");

        ArrayList<String> str3 = Lists.newArrayList();
        str3.add("str3");

        col.add(str1);
        col.add(str2);
        col.add(str2);

//        System.out.println(col.get(0).toString());
//        col.get(0).clear();
//        System.out.println(col.get(0).toString());


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        for (int i=0;i<col.size();i++){
            int finalI = i;
            executor.schedule(()->{
                System.out.println(finalI);
            },5*i,TimeUnit.SECONDS);
        }

    }

    public static Boolean test() throws ExecutionException, RetryException {
        ArrayList<String> str1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            str1.add("str" + i);
        }
        int max = str1.size();
        //定义重试机制
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))

                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))

                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(max))

                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

                .build();

        //定义请求实现
        Callable<Boolean> callable = new Callable<Boolean>() {
            int index = 0;

            @Override
            public Boolean call() throws Exception {
                log.info("call index={}", index);
                log.info(str1.get(index));
                index++;
                return true;
            }
        };
        //利用重试器调用请求
        return retryer.call(callable);
    }
}
