package code.feign;

import code.config.FeignConfig;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "could-eureka-producer", fallbackFactory = FallbackTestFeignClient.FallbackTestFeignClientFallbackFactory.class)
public interface FallbackTestFeignClient {

    @GetMapping("/fallbackTest/error500")
    String error500();

    @GetMapping("/fallbackTest/errorTimeout")
    String errorTimeout();

    @GetMapping("/fallbackTest/errorThreadNotEnough")
    String errorThreadNotEnough();


    @Component
    public static class FallbackTestFeignClientFallbackFactory implements FallbackFactory<FallbackTestFeignClient> {

        @Override
        public FallbackTestFeignClient create(Throwable cause) {
            return new FallbackTestFeignClient() {

                @Override
                public String error500() {
                    return "fallback method error500";
                }

                @Override
                public String errorTimeout() {
                    return "fallback method errorTimeout";
                }

                @Override
                public String errorThreadNotEnough() {
                    return "fallback method errorThreadNotEnough";
                }
            };
        }
    }
}
