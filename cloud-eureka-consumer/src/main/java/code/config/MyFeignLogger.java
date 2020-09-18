package code.config;

import feign.Feign;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 自定义feign日志打印
 * 这样的话在打印日志时显示的是对应的请求线程
 * 且配置文件中或logback文件配置的feign日志级别不生效
 * @see feign.Logger
 * @see feign.slf4j.Slf4jLogger
 * @see org.springframework.cloud.openfeign.DefaultFeignLoggerFactory
 * @see org.springframework.cloud.openfeign.FeignClientsConfiguration#feignLoggerFactory()
 */
//@Configuration
//@ConditionalOnClass(Feign.class)
public class MyFeignLogger extends feign.Logger {


    private Logger logger;

    public MyFeignLogger() {
        this(LoggerFactory.getLogger(feign.Logger.class));
    }

    MyFeignLogger(Logger logger) {
        this.logger = logger;
    }



    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isInfoEnabled()) {
            super.logRequest(configKey, logLevel, request);
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        if (logger.isInfoEnabled()) {
            return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            //这里要用info日志级别，否则打印不出来日志
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }
}