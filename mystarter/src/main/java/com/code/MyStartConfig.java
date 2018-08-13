package com.code;

import com.code.domain.HelloProperties;
import com.code.service.HelloService;
import com.code.service.HelloServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration

@EnableConfigurationProperties({HelloProperties.class})
@ConditionalOnClass
public class MyStartConfig {

    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService(){
        System.out.println(">>>The HelloService Not Found，Execute Create New Bean.");
        HelloService helloService = new HelloServiceImpl();
        //设置消息内容
        helloService.sayHello("test");
        return helloService;
    }

}
