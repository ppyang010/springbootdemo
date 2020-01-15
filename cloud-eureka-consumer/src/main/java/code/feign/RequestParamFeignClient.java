package code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "could-eureka-producer")
public interface RequestParamFeignClient {

    @GetMapping("/requestParamTest")
    String requestParamTest(@RequestParam("username") String username);
}
