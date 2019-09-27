package com.code.demo;

import com.code.demo.example.DemoConfigProperties;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableApolloConfig
@EnableConfigurationProperties({DemoConfigProperties.class})
public class DemoApolloClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApolloClientApplication.class, args);
    }

}
