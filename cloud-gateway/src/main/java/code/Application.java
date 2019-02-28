package code;


import code.filter.RequestTimeGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author ccy
 */
//@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import(RouterConfig.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}


}
