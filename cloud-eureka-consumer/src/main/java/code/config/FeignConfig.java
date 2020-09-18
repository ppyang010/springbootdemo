package code.config;

import cn.hutool.json.JSONUtil;
import code.suport.InMemoryMultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Collection;
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


    /**
     * 支持文件下载
     * https://github.com/spring-cloud/spring-cloud-netflix/issues/2246
     */
    @Bean
    @ConditionalOnMissingBean
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters) {

        Decoder decoder = (response, type) -> {
            //支持文件下载
            if (type instanceof Class && MultipartFile.class.isAssignableFrom((Class) type)) {
                Collection<String> contentTypes = response.headers().get("content-type");
                String contentType = "application/octet-stream";
                if (contentTypes.size() > 0) {
                    String[] temp = new String[contentTypes.size()];
                    contentTypes.toArray(temp);
                    contentType = temp[0];
                }
                Collection<String> dispostions = response.headers().get("Content-Disposition");
                System.out.println(JSONUtil.toJsonStr(dispostions));
                byte[] bytes = StreamUtils.copyToByteArray(response.body().asInputStream());
                InMemoryMultipartFile inMemoryMultipartFile = new InMemoryMultipartFile("file","", contentType,bytes);
                return inMemoryMultipartFile;
            }
            return new SpringDecoder(messageConverters).decode(response, type);
        };
        return new ResponseEntityDecoder(decoder);
    }


    @Bean
    @ConditionalOnMissingBean
    public Encoder encoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignLoggerFactory infoFeignLoggerFactory(){
        return new InfoFeignLoggerFactory();
    }
}
