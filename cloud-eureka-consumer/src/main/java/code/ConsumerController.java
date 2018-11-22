package code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午2:13
 */
@RestController
public class ConsumerController {
    @Autowired
    private RemoteService remoteService;

    /**
     * Feign 方式
     */
    @GetMapping("/hello1")
    public String hello1(@RequestParam String name) {
        return remoteService.hello(name);
    }
}
