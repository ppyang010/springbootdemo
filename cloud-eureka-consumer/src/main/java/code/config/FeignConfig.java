package code.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

/**
 * @author ccy
 */
@Configuration
public class FeignConfig {

    /**
     * Jackson时间参数转换问题，默认的TimeZone并不是东八区，而是UTC。
     * 使用@Primary 设置优先使用这个bean
     * 这样就不需要手动将objectMapper添加到HttpMessageConverter
     * HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
     *
     * https://segmentfault.com/a/1190000014830496
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringDecoder(messageConverters);
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder encoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
