package code;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午2:13
 */
@RestController
public class ProduceController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam String name){
        String s = "hello " + name;
        System.out.println(s);
        return s;
    }
}
