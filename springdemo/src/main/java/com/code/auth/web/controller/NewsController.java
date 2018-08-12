package com.code.auth.web.controller;

import com.code.auth.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ccy
 */
@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;

    @GetMapping("/init")
    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int x = 0; x<100; x++){
            executorService.execute(()->{
                for (int i = 0; i < 10000; i++) {
                    System.out.println("线程" + Thread.currentThread().getName());
                    System.out.println("第" + i + "条数据生成");
                    newsService.init();
                }
            });
        }
    }
}
