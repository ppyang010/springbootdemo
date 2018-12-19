package com.code;

import com.code.annotation.enable.EnableBean;
import com.code.auth.config.DemoConfig;
import com.code.auth.config.ShiroConfig;
import com.code.auth.dao.NewsDao;
import com.code.auth.service.NewsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;


/**
 * @author ccy
 */
@SpringBootApplication
@EnableBean
@Import({ShiroConfig.class, DemoConfig.class})
public class SpringDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApplication.class, args);
		SpringApplication springApplication = new SpringApplication(SpringDemoApplication.class);
//		// 设置为 非 web 应用
//		springApplication.setWebEnvironment(false);
		ConfigurableApplicationContext context = springApplication.run(args);
        NewsDao bean = context.getBean(NewsDao.class);
        bean.findOne(1);
        System.out.println("end");
//		System.out.println(context.getBean(SpringDemoApplication.class));
//		// 输出当前 Spring Boot 应用的 ApplicationContext 的类名
//		System.out.println("当前 Spring 应用上下文的类：" + context.getClass().getName());

    }
}
