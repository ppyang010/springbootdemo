package com.code.auth.config;

import com.code.auth.shiro.realm.LoginRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/12/28.
 * @author ccy
 */
@Configuration
public class ShiroConfig {
    @Bean
    public LoginRealm getLoginRealm(){
        LoginRealm realm= new LoginRealm();
//        realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor=new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm loginRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(loginRealm);
        return defaultWebSecurityManager;
    }
}
