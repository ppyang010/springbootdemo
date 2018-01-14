package com.code.auth.config;

import com.code.auth.shiro.realm.LoginRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    //shiro生命周期处理器
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor=new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
    //安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm loginRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(loginRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 基于表单的身份验证过滤器
     * @return
     */
    @Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter getFormAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setLoginUrl("/user/login");
        formAuthenticationFilter.setSuccessUrl("/index");
        return formAuthenticationFilter;
    }

    /**
     * 相当于调用SecurityUtils.setSecurityManager(securityManager);
     * @param securityManager
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(DefaultWebSecurityManager securityManager){
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new DefaultSecurityManager[]{securityManager} );
        return bean;
    }


    /**
     * shiro web过滤器
     */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,FormAuthenticationFilter formAuthenticationFilter){
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("authc",formAuthenticationFilter);
        Map <String,String> map =new HashMap<>();
        map.put("/hello","authc");
        map.put("/admin","authc,roles[admin],perms[admin:create]");

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/user/login");
        factoryBean.setUnauthorizedUrl("unauthorized");
        factoryBean.setFilters(filterHashMap);
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }


}
