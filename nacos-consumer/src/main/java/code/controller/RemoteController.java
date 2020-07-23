package code.controller;

import code.feign.ProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ccy
 * @description
 * @time 2020-07-23 15:02
 */
@RestController
public class RemoteController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate loadBalancerRestTemplate;

    @Autowired
    private ProducerClient producerClient;

    @GetMapping("/remote/hello")
    public String remoteHello() {
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-producer");
        String url = serviceInstance.getUri() + "/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "Invoke : " + url + ", return : " + result;
    }


    @GetMapping("/remote/hello/rest")
    public String remoteHelloRest() {
        String result = loadBalancerRestTemplate.getForObject("http://nacos-producer/hello", String.class);
        return "Return : " + result;
    }

    @GetMapping("/remote/hello/feign")
    public String remoteHelloFeign() {
        return "Return : " + producerClient.hello();
    }
}
