package code;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccy
 */
@Configuration
public class RouterConfig {

    /**
     * 使用代码方式配置路由
     *
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/jd")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://jd.com:80/").id("jd_route"))
                .route(p -> p
                        .path("/bili")
                        .uri("http://www.bilibili.com:80/").id("bili_route"))
                .route(r -> r
                        .path("/default/**")
                        .filters(f -> f.addRequestHeader("head", "hello head")
                                .addRequestParameter("param","hello")
                                .addResponseHeader("respParam","hello"))
                        .uri("forward:/default").id("default_route"))
                .route(r -> r
                        .path("/target/**")
                        .filters(f -> f.addRequestHeader("head", "hello head")
                                .addRequestParameter("param","hello")
                                .addResponseHeader("respParam","hello")
                                .hystrix(config -> config
                                        .setName("target_route_fallback")
                                        .setFallbackUri("forward:/fallback")))
                        .uri("http://127.0.0.1:7720/gateway/target").id("target_route"))
                .build();
    }

}
