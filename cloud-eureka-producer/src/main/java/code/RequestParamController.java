package code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RequestParam 注解功能测试
 * 有注解的情况下
 * 1.使用postman 不携带username参数 不能进入方法 报错 返回400
 * 2.使用postman username = 空(字面意思 不填数据)  能进入方法 usernam=""
 * 3.在consumer 用feign 调用  username =null  会报错
 * 没有注解的情况下
 * 3.在consumer 用feign 调用  username =null  不会报错 收到为null
 * @author ccy
 */
@RestController
public class RequestParamController {

    @GetMapping("/requestParamTest")
    public String requestParamTest(String username) {
        return username;
    }
}
