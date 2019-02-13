package com.code.data;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ccy
 * @description
 * @time 2019/1/2 上午11:28
 */
@Aspect
@Component
public class AopTest {
    @Pointcut("execution(* com.code.data.DataController.*(..))")
    public void controllerMethodPointcut() {
    }


    /**
     * 方法成功return之后的advice
     * @param point
     * @param rtv
     */
    @AfterReturning(value = "controllerMethodPointcut()", returning = "rtv")
    public void after(JoinPoint point, Object rtv) {

        System.out.println(point);
        System.out.println(rtv);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("moudle","demo");
    }

    public void before(){

    }
}
