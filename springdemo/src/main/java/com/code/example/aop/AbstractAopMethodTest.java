package com.code.example.aop;

import com.code.annotation.aop.AopTest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ccy
 * @description
 * @time 2019-12-18 17:31
 */
@Slf4j
public abstract class AbstractAopMethodTest {

    @AopTest
    public void testMethod(){
        log.info("AbstractAopMethodTest testMethod");
    }
}
