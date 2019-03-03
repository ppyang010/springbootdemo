package code.filter;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.json.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 限流拦截器
 *
 * @author ccy
 */
@Component
public class LimitFilter implements GlobalFilter, Ordered {

    private static TimedCache<String, Object> cache;
    private static int max = 10;

    @PostConstruct
    public void init() {
        cache = CacheUtil.newTimedCache(1000 * 60);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String key = remoteAddress.getHostString() + remoteAddress.getPort();
        int count = add(key);
        if (count > max) {
            return write(exchange, response, "请求次数过多");
        }
        return chain.filter(exchange);
    }

    private int add(String key) {
        int count = 0;
        Integer num = (Integer) cache.get(key, false);
        if (Objects.nonNull(num)) {
            count = num + 1;
        }
        cache.put(key, count);
        System.out.println("count=" + count);
        return count;
    }

    private Mono<Void> write(ServerWebExchange exchange, ServerHttpResponse response, String resText) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(resText.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -400;
    }
}
