package com.code.demo.example;

import cn.hutool.json.JSONUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ccy
 */
@RestController
@Slf4j
public class ExampleController {
    @Autowired
    private DemoConfigProperties demoConfigProperties;

    /**
     * Spring方式也可以结合API方式使用，如注入Apollo的Config对象，就可以照常通过API方式获取配置了：
     * 使用参考:https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#322-spring-placeholder%E7%9A%84%E4%BD%BF%E7%94%A8
     */
    @ApolloConfig
    private Config config;

    @Value("${demo.config.name:default-name-value}")
    private String name;

    /**
     * appllo 上配置为 demo.config.jsonItem = {"username":"ccy","age":18}
     * 支持接受  对像,对像数组
     */
    @ApolloJsonValue("${demo.config.jsonItem}")
    private Map jsonItem;


    @GetMapping("/test/printf")
    public String printfDemoConfigProperties() {
        log.info(JSONUtil.toJsonStr(demoConfigProperties));
        return JSONUtil.toJsonStr(demoConfigProperties);
    }


    @GetMapping("/test/name")
    public String printfName() {
        return name;
    }

    @GetMapping("/test/api")
    public String api(String key) {
        //config instance is singleton for each namespace and is never null
        Config config = ConfigService.getAppConfig();
        String someKey = key;
        String someDefaultValue = "default-key-value-api";
        String value = config.getProperty(someKey, someDefaultValue);
        return value;
    }

    @GetMapping("/test/api2")
    public String api2(String key) {
        //config instance is singleton for each namespace and is never null
        String someKey = key;
        String someDefaultValue = "default-key-value-api";
        String value = config.getProperty(someKey, someDefaultValue);
        return value;
    }


    @GetMapping("/test/json")
    public String json(String key) {
        log.info("{}", JSONUtil.toJsonStr(jsonItem.keySet()));
        return JSONUtil.toJsonStr(jsonItem);
    }
}
