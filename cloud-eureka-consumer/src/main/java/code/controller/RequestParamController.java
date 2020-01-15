package code.controller;

import code.feign.RequestParamFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy
 * @RequestParam 注解功能测试
 * 服务提供方有注解
 * 关联cloud-eureka-producer 项目的 RequestParamController类
 * 3.在consumer 用feign 调用  username =null  传null会报错
 *
 * 服务提供方无注解
 * 在consumer 用feign 调用  username =null   不会报错 收到为null
 */
@RestController
public class RequestParamController {

    @Autowired
    private RequestParamFeignClient requestParamFeignClient;

    @GetMapping("/requestParamTest")
    public String requestParamTest() {
        return requestParamFeignClient.requestParamTest(null);
    }
}
