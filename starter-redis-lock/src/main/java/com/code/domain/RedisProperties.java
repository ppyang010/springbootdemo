package com.code.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis 配置文件
 * @author ccy
 */
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    @Override
    public String toString() {
        return "RedisProperties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
