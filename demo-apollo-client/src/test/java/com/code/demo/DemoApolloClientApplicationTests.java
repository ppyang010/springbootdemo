package com.code.demo;

import cn.hutool.json.JSONUtil;
import com.code.demo.example.DemoConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApolloClientApplicationTests {
    @Autowired
    private DemoConfigProperties demoConfigProperties;

    @Test
    public void contextLoads() {
    }


    @Test
    public void printfDemoConfigProperties(){
        log.info(JSONUtil.toJsonStr(demoConfigProperties));
    }
}
