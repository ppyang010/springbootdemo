package com.code.auth.FactoryBean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ccy
 * @description
 * @time 2018/12/14 上午11:09
 * https://blog.51cto.com/4247649/2118349
 * spring bean 生命周期 方法执行顺序  InitializingBean BeanPostProcessor
 * <p>
 * <p>
 * 调用BeanLife构造方法....
 * BeanLife setName()
 * BeanLife setLifeInner()
 * MyBeanPostProcessor before class = BeanLife
 * 调用初始化方法.... PostConstruct()
 * 调用InitializingBean.afterPropertiesSet().....
 * MyBeanPostProcessor after class = BeanLife
 */
@Component
public class BeanLife implements InitializingBean, DisposableBean {

    private String name;

    private LifeInner lifeInner;

    @Autowired
    public BeanLife setLifeInner(LifeInner lifeInner) {
        System.out.println("BeanLife setLifeInner()");
        this.lifeInner = lifeInner;
        return this;
    }

    public BeanLife() {
        System.out.println("调用BeanLife构造方法....");
    }

    @Value("beanLifeName")
    public BeanLife setName(String name) {
        System.out.println("BeanLife setName()");
        this.name = name;
        return this;
    }

    /**
     * 初始化方法的注解方式  等同与init-method=init
     */
    @PostConstruct
    public void init() {
        System.out.println("调用初始化方法.... PostConstruct()");
    }

    /**
     * 销毁方法的注解方式  等同于destory-method=destory
     */
    @PreDestroy
    public void destoryMethod() {
        System.out.println("调用销毁化方法....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy().....");

    }

    /**
     * InitializingBean 接口实现方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet().....");
    }
}
