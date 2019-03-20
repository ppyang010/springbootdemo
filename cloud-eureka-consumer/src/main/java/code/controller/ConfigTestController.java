package code.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ccy
 * spring config 配置中心
 */
@RestController
public class ConfigTestController {
    @Value("${form.remark}")
    private String remark;

    @Value("${form.profile}")
    private String profile;

    @GetMapping("/config/test")
    public String test() {
        return profile + ":" + remark;
    }
}
