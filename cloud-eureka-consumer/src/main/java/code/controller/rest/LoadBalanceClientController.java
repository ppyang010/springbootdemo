package code.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ccy
 * loadBalancerClient 方式 demo
 */
@RestController
public class LoadBalanceClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * loadBalancerClient 方式
     * 有负载均衡
     * http://blog.didispace.com/spring-cloud-starter-dalston-2-1/
     */
    @GetMapping("/hello0")
    public String hello0(@RequestParam String name) {
//        远程服务名，即spring.application.name配置的名称
        ServiceInstance serviceInstance = loadBalancerClient.choose("could-eureka-producer");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello?name=" + name;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }


}
