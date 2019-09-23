package com.code.demo.example;

import cn.hutool.json.JSONUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy
 */
@RestController
@Slf4j
public class ExampleController {
    @Autowired
    private DemoConfigProperties demoConfigProperties;

    @Value("${demo.config.name:default-name-value}")
    private String name;

    @GetMapping("/test/printf")
    public String printfDemoConfigProperties(){
        log.info(JSONUtil.toJsonStr(demoConfigProperties));
        return JSONUtil.toJsonStr(demoConfigProperties);
    }


    @GetMapping("/test/name")
    public String printfName(){
        return name;
    }

    @GetMapping("/test/api")
    public String api(String key){
        //config instance is singleton for each namespace and is never null
        Config config = ConfigService.getAppConfig();
        String someKey = key;
        String someDefaultValue = "default-key-value-api";
        String value = config.getProperty(someKey, someDefaultValue);
        return value;
    }
}
