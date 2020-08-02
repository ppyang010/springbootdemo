package com.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ccy
 * @description
 * @time 2020-07-31 16:53
 */
@SpringBootApplication
@MapperScan("com.code.mapper")
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication springApplication = new org.springframework.boot.SpringApplication(SpringApplication.class);
        //设置为 非 web 应用
        //springApplication.setWebEnvironment(false);
        ConfigurableApplicationContext context = springApplication.run(args);
    }
}
