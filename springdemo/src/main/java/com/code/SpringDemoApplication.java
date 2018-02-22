package com.code;

import com.code.auth.config.ShiroConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


/**
 * @author ccy
 */
@SpringBootApplication
@Import(ShiroConfig.class)
public class SpringDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		/*
         * Banner.Mode.OFF:关闭;
         * Banner.Mode.CONSOLE:控制台输出，默认方式;
         * Banner.Mode.LOG:日志输出方式;
         */
//		application.bannerMode(Banner.Mode.OFF);
		return application.sources(SpringDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
}
