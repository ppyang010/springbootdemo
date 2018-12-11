package code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午5:49
 */
@RestController
public class RibbonController {
    @Resource(name = "ribbonRestTemplate")
    private RestTemplate restTemplate;


    /**
     * loadBalancerClient 方式2
     * 有负载均衡
     * http://blog.didispace.com/spring-cloud-starter-dalston-2-2/
     */
    @GetMapping("/hello2")
    public String hello2(@RequestParam String name) {
//        远程服务名，即spring.application.name配置的名称
        return restTemplate.getForObject("http://could-eureka-producer/hello?name=" + name, String.class);
    }
}
