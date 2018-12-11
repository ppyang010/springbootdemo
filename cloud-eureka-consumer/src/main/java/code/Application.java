package code;


import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ccy
 *
 * //@EnableDiscoveryClient :启用服务注册与发现
 * //@EnableFeignClients：启用feign进行远程调用
 * //@EnableCircuitBreaker:启用Hystrix
 * //@SpringBootApplication
 * //@SpringCloudApplication 包含 134
 */

//@EnableDiscoveryClient
//
//@SpringBootApplication
//@EnableCircuitBreaker


@EnableFeignClients
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean("ribbonRestTemplate")
	@LoadBalanced
	public RestTemplate ribbonRestTemplate(){
		return new RestTemplate();
	}



}
