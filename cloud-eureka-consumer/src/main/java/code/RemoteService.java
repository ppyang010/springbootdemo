package code;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午3:00
 *
 * name:远程服务名，即spring.application.name配置的名称
 */
@FeignClient(name= "could-eureka-producer")
public interface RemoteService {
    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

}
