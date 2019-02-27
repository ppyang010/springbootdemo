package code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;
import java.util.Optional;

/**
 * 用于测试路由跳转
 * @author ccy
 */
@RestController
public class DefalutController {

    @GetMapping("/default")
    public String defaultMethod(){
        System.out.println("这里是default方法");
        return "这里是default方法";
    }

    @GetMapping("/fallback")
    public String fallback(){
        System.out.println("这里是fallback方法");
        return "这里是fallback方法";
    }
}
