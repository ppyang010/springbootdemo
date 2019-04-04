package com.code.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy
 */
@RestController
public class CacheTestController {
    @Autowired
    private CacheTestService cacheTestService;


    @GetMapping("/cache/simple")
    public String simpleTest() {
        String simpleTest1 = cacheTestService.simpleCache1("simpleTest1");
        return simpleTest1;
    }

    @GetMapping("/cache/hutool")
    public String hutoolTest1() {
        String res1 = cacheTestService.hutoolCache1("hutoolTest1");
        String res2 = cacheTestService.dontUseGlobalProperties("dontUseGlobalProperties");

        return res1 + "-" + res2;
    }
}
