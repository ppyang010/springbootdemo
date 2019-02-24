package com.code.auth.FactoryBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author ccy
 * @description
 * @time 2018/12/14 上午11:58
 */
@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        log.debug("MyBeanPostProcessor before class = " + o.getClass().getSimpleName());
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        log.debug("MyBeanPostProcessor after class = " + o.getClass().getSimpleName());
        return o;
    }
}
