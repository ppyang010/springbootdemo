package com.code.data.hello;

import com.code.MyApplicationContext;
import com.code.annotation.enable.EnableBeanTest;
import com.code.lock.SimpleRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017/10/3.
 */
@Slf4j
@RestController
public class HelloController {
    public static Integer STOCK;

    @Value("${myParam}")
    private String myParam;

    @Value("${file.freemarker.use}")
    private boolean freemarkerUse;
    @Value("${file.datasource.use}")
    private boolean datasourceUse;

    @Autowired
    private People people;
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SimpleRedisLock simpleRedisLock;
    @Autowired
    private MyApplicationContext myApplicationContext;

    @RequestMapping(value = {"hello", "hi"}, method = RequestMethod.GET)
    public String hello() {
        return "hello spring boot!!! MyParam = " + myParam;
    }
    @RequestMapping(value = {"fileIsUse"}, method = RequestMethod.GET)
    public String fileIsUse() {
        return "fileIsUse = " + datasourceUse;
    }

    @GetMapping("/enableBeanTest")
    public void enableBeanTest() {
        System.out.println(myApplicationContext.getBean(EnableBeanTest.class));
    }

    /**
     * 测试spring 属性 组成对象
     * @return
     */
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return people.getName();
    }


    /**
     * @return
     */
    @RequestMapping(value = "helloPrintLog", method = RequestMethod.GET)
    public String helloPrintLog() {
        log.trace("trace log !!!");
        log.debug("debug log !!!");
        log.info("info log !!!");
        log.warn("warn log !!!");
        log.error("error log !!!");
        return "ok";
    }





}
