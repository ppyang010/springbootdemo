package com.code.example.aop;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ccy
 */
@RestController
public class AopController {

    @Autowired
    private AopMethodTest aopMethodTest;

    @GetMapping("/aoptest")
    public Map apoTest() {
        aopMethodTest.testMethod();
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("code", "ok");
        return map;
    }
}
