package com.code.auth.FactoryBean;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author ccy
 * @description
 * @time 2018/12/13 上午11:33
 */
public class MyFactoryBean extends AbstractFactoryBean<Bean> {
    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    protected Bean createInstance() throws Exception {
        return null;
    }
}
