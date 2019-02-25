package code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试路由跳转
 * @author ccy
 */
@RestController
public class DefalutController {

    @GetMapping("/default")
    public String Default(){
        System.out.println("这里是default方法");
        return "这里是default方法";
    }
}
