package code.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 全局拦截器
 *
 * @author ccy
 */
public class TokenFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return emptyToUnAuth(exchange, chain);
    }

    private Mono<Void> emptyToUnAuth(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (Objects.isNull(token) || token.isEmpty()) {
            System.out.println("token 为空,设置返回头状态为401");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private Mono<Void> emptyToNewOne(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (Objects.isNull(token) || token.isEmpty()) {
            System.out.println("token 为空,设置新值");
            int random = Double.valueOf(Math.random() * 100).intValue();
            exchange.getAttributes().put("token",String.valueOf(random));
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器的优先级
     * 值越小优先级越高
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
