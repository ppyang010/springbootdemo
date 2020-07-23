package code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("nacos-producer")
public interface ProducerClient {

    @GetMapping("/hello")
    String hello();

}