package code.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ccy
 * nacos配置可通过url直接访问如 http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos-producer.properties&group=DEFAULT_GROUP
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${demo.config.text:}")
    private String text;

    @GetMapping("/text")
    public String text() {
        return text;
    }
}
