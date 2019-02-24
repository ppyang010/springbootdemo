package com.code.data.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/3.
 * @Component
 * @ConfigurationProperties(prefix = "people")
 * 用于接受配置文件中的参数
 */
@Component
@ConfigurationProperties(prefix = "people")
public class People {
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
