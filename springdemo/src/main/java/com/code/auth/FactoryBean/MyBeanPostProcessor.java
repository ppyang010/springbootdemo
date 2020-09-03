package com.code.auth.FactoryBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ccy
 * @description
 * @time 2018/12/14 上午11:58
 */
@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @PostConstruct
    public void init() {
        log.info("MyBeanPostProcessor init");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        String simpleName = o.getClass().getSimpleName();
        if (simpleName.contains("BeanLife")) {
//            log.info("MyBeanPostProcessor before class = " + simpleName);
            System.out.println("MyBeanPostProcessor before class = " + simpleName);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        String simpleName = o.getClass().getSimpleName();
        if (simpleName.contains("BeanLife")) {
//            log.info("MyBeanPostProcessor after class = " + o.getClass().getSimpleName());
            System.out.println("MyBeanPostProcessor after class = " + o.getClass().getSimpleName());
        }
        return o;
    }
}
