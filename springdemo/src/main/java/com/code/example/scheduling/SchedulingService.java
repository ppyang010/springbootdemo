package com.code.example.scheduling;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.nio.ch.Util;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author ccy
 * @description
 * @time 2019/3/15 下午4:46
 */
@Component
@Slf4j
public class SchedulingService {
    private static volatile boolean enable = true;


    @Scheduled(cron = "0/10 * * * * ?")
    public void schedulingA() {
        log.info("schedulingA method run thread name={}",Thread.currentThread().getName());
//        if (enable) {
//            enable = false;
//            log.info("enable = false");
//            ArrayList<Object> objects = Lists.newArrayList();
//            while (true) {
//                try {
//                    int size = 3 * 1024 * 1024;
//                    ByteBuffer temporaryDirectBuffer = Util.getTemporaryDirectBuffer(size);
//                    objects.add(temporaryDirectBuffer);
//                    log.info("create ByteBuffer");
//                    Thread.sleep(5000);
//                } catch (Exception e) {
//                    enable = true;
//                    log.info("enable = true");
//                    e.printStackTrace();
//                    break;
//                }
//
//            }
//
//        }


    }
}
