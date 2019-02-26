package code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * gateway 路由跳转的目标地址
 * @author ccy
 */
@RestController
public class GatewayTargetController {

    @GetMapping("/gateway/target")
    public String target(HttpServletRequest request){
        System.out.println(request.getHeader("hello"));
        return "target return";
    }
}
