package com.code.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/10/3.
 */
@RestController
public class HelloController {

    @Value("${myParam}")
    private String myParam;

    @Autowired
    private People people;

    @RequestMapping(value = {"hello","hi"},method = RequestMethod.GET)
    public String hello(){
        return "hello spring boot!!! MyParam = "+myParam;
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return people.getName();
    }

}
