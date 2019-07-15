package com.code;

import com.code.domain.HelloProperties;
import com.code.service.HelloService;
import com.code.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

@EnableConfigurationProperties({HelloProperties.class})
//@ConditionalOnClass(HelloService.class)//存在HelloService时初始化该配置类
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (
                prefix = "hello",//存在配置前缀hello
                value = "enabled",//开启
                matchIfMissing = true//缺失检查
        )
public class MyStartConfig {

    /**
     * application.properties配置文件映射前缀实体对象
     */
    @Autowired
    private HelloProperties helloProperties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        System.out.println("my Starter 装载测试");
        System.out.println(">>>The HelloService Not Found，Execute Create New Bean.");
        HelloService helloService = new HelloServiceImpl();
        //设置消息内容
        helloService.sayHello("init" + helloProperties.getMsg());
        return helloService;
    }

}
