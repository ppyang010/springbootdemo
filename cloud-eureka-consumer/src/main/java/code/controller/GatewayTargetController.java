package code.controller;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * gateway 路由跳转的目标地址
 *
 * @author ccy
 */
@RestController
public class GatewayTargetController {

    @GetMapping("/gateway/target")
    public String target(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("----request head----");
        System.out.println(request.getHeader("head"));
        System.out.println("----request param----");
        System.out.println(request.getQueryString());
        System.out.println("----response head----");
        System.out.println(response.getHeader("respHead"));
        System.out.println(request.getHeader("hello"));
        return "target return";
    }

    @GetMapping("/gateway/target/hystrix")
    public String targetHystrix(@RequestParam boolean isSleep) throws InterruptedException {
        System.out.println("isSleep=" + isSleep);
        if(isSleep){
            TimeUnit.SECONDS.sleep(10);
        }

        return "targetHystrix return";
    }
}
