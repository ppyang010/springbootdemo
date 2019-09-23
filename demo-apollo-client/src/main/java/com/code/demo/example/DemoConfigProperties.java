package com.code.demo.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ccy
 */
@Component
@ConfigurationProperties(prefix = "demo.config")
@Data
public class DemoConfigProperties {
    private String name;
}
