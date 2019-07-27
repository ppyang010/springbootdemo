package com.code.auth.config;

import com.code.util.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccy
 * @description
 * @time 2018/12/14 上午11:18
 */
@Configuration
public class DemoConfig {

    @Bean
    public Sequence sequence(){
        return new Sequence();
    }
}
