package com.code;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author ccy
 * @description
 * @time 2018/10/19 下午5:02
 */
@Component
public class MyApplicationContext implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public Object getBean(Class clazz){
        return applicationContext.getBean(clazz);
    }
}
