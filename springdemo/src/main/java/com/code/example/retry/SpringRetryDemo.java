package com.code.example.retry;

import cn.hutool.core.util.RandomUtil;
import com.code.auth.exception.CodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.stats.DefaultStatisticsRepository;
import org.springframework.retry.stats.StatisticsListener;
import org.springframework.retry.support.RetryTemplate;

import static com.code.auth.exception.ExceptionCode.UNKOWN_ERROR;

/**
 * @author ccy
 */
@Slf4j
public class SpringRetryDemo {


    public static void main(String[] args) throws Throwable {
        // 新建一个模板，可用作为我一个spring bean注入进来
        RetryTemplate template = new RetryTemplate();
        RetryCallback<String, Throwable> retryCallback = context -> remoteInvoke();
        RecoveryCallback<String> recoveryCallback = context -> {
            System.out.println("recovery");
            return "recovery";
        };
        // 设置回避策略 指的是每次重试是立即重试还是等待一段时间后重试。默认情况下是立即重试
        template.setBackOffPolicy(new FixedBackOffPolicy());
        // 设置重试策略
        template.setRetryPolicy(new SimpleRetryPolicy(5));
        // 设置listener
        template.setListeners(new RetryListener[]{new StatisticsListener(new DefaultStatisticsRepository())});
        // 执行模板
        template.execute(retryCallback, recoveryCallback);
    }


    private static String remoteInvoke() {
        int i = RandomUtil.randomInt(0, 100);

        log.info("模拟业务调用,i={}", i);
        if (i > 10) {
            throw new CodeException(UNKOWN_ERROR);
        }
        return String.valueOf(i);
    }
}
