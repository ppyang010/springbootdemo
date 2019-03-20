package code.controller.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ccy
 * hystrix 服务降级demo
 * http://blog.didispace.com/spring-cloud-starter-dalston-4-1/
 * @time 2018/12/11 上午11:27
 */
@RestController
public class HystrixController {
    @Autowired
    ConsumerService consumerService;

    @GetMapping("/helloDC")
    public String dc() {
        return consumerService.consumer();
    }

    @Service
    class ConsumerService {

        @Resource(name = "ribbonRestTemplate")
        private RestTemplate restTemplate;

        /**
         * 默认超时时间是2000ms
         * @return
         */
        @HystrixCommand(fallbackMethod = "fallback")
        public String consumer() {
            //远程服务名，即spring.application.name配置的名称
            return restTemplate.getForObject("http://could-eureka-producer/slow", String.class);
        }

        /**
         * fallback的参数要和consumer保持一致
         * 降级服务方法
         * 当调用时间过长,出现异常等
         * @return
         */
        public String fallback() {
            return "fallback";
        }

    }
}
