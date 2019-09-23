package com.code.demo;

import com.code.demo.example.DemoConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({DemoConfigProperties.class})
public class DemoApolloClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApolloClientApplication.class, args);
    }

}
