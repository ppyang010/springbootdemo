package code;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ccy
 * @description spring config 配置中心
 * @time 2018/11/28 下午6:17
 */
@RestController
public class ConfigTestController {
    @Value("${form.remark}")
    private String remark;

    @GetMapping("/config/test")
    public String test(){
        return remark;
    }
}
