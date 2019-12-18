package com.code.annotation.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 */
@Component
@Aspect
public class AspectTest {

    @Pointcut("@annotation(com.code.annotation.aop.AopTest)")
    public void pointcut(){

    }


}
