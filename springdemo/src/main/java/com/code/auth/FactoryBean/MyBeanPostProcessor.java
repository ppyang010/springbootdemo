package com.code.auth.FactoryBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author ccy
 * @description
 * @time 2018/12/14 上午11:58
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanPostProcessor before class = " + o.getClass().getSimpleName());
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanPostProcessor after class = " + o.getClass().getSimpleName());
        return o;
    }
}
