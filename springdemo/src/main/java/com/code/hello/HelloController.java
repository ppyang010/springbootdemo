package com.code.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/10/3.
 */
@RestController
public class HelloController {

    @Value("${myParam}")
    private String myParam;

    @Autowired
    private People people;
    @Autowired
    private Jedis jedis;

    @RequestMapping(value = {"hello","hi"},method = RequestMethod.GET)
    public String hello(){
        return "hello spring boot!!! MyParam = "+myParam;
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return people.getName();
    }

    @RequestMapping(value = "redis",method = RequestMethod.GET)
    public String redisTest(String val){
        String res = jedis.set("test", val);
        System.out.println(res);
        return jedis.get("test");
    }

}
