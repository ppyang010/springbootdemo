package com.code.example.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ccy
 * @description
 * @time 2019/3/15 下午4:46
 */
@Component
@Slf4j
public class SchedulingService {


    @Scheduled(cron = "0/30 * * * * ?")
    public void schedulingA(){
        log.info("schedulingA method run");
    }
}
