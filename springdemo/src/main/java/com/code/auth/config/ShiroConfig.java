package com.code.auth.config;

import com.code.auth.shiro.MyFormAuthenticationFilter;
import com.code.auth.shiro.realm.LoginRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
    /**
     * shiro web过滤器
     * 这个要放在最前面 用于处理一些奇奇怪怪的问题
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,FormAuthenticationFilter formAuthenticationFilter){
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("authc",formAuthenticationFilter);
        Map <String,String> map =new HashMap<>();
        map.put("/index","authc");
        map.put("/**","anon");
//        map.put("/admin","authc,role[admin],perms[admin:create]");

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/admin/login");
        factoryBean.setUnauthorizedUrl("/index");
        factoryBean.setFilters(filterHashMap);
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public LoginRealm getLoginRealm(){
        LoginRealm realm= new LoginRealm();
//        realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }

    //shiro生命周期处理器
    @Bean("lifecycleBeanPostProcessor")
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
        FormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setLoginUrl("/admin/login");
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
        bean.setArguments(new DefaultWebSecurityManager[]{securityManager} );
//        bean.setArguments(securityManager);
        return bean;
    }




    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
