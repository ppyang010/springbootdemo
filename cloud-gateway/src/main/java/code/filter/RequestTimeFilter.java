package code.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义 gateway filter
 * @author ccy
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //这部分相当于pre filter
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange)
                //然后再chain.filter的内部类中的run()方法中相当于"post"过滤器，在此处打印了请求所消耗的时间
                .then(Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        System.out.println(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }));
    }


    /**
     * Ordered中的int getOrder()方法是来给过滤器设定优先级别的，值越大则优先级越低
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
