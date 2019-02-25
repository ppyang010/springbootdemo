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

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/route/first")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://127.0.0.1:9000/default"))
                .build();
    }

}
