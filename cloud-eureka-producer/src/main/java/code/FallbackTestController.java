package code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试触发fallback的方式 服务提供方
 * 测试会触发进入fallback 方法的情况
 * 1.服务提供者返回500
 * 2.接口调用超时
 * 3.熔断器打开
 * 4.资源不足
 * 4.1线程池
 * 4.2信号量
 */
@RestController
@RequestMapping("/fallbackTest")
public class FallbackTestController {

    @GetMapping("/error500")
    public String error500() {
        int x = 100 / 0;
        return String.valueOf(x);
    }

    @GetMapping("/errorTimeout")
    public String errorTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);
        System.out.println("errorTimeout sleep end");
        return "errorTimeout success";
    }
    @GetMapping("/errorThreadNotEnough")
    public String errorThreadNotEnough() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("errorThreadNotEnough log");
        return "errorThreadNotEnough success";
    }
}
