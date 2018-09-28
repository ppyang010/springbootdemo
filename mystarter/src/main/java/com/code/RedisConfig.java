package com.code;

import com.code.domain.HelloProperties;
import com.code.domain.RedisProperties;
import com.code.service.HelloService;
import com.code.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration

@EnableConfigurationProperties({RedisProperties.class})
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (
                //存在配置前缀hello
                prefix = "redis",
                value = "enabled",//开启
                matchIfMissing = true//缺失检查
        )
public class RedisConfig {

    /**
     * application.properties配置文件映射前缀实体对象
     */
    @Autowired
    private RedisProperties redisProperties;


    @Bean
    @ConditionalOnMissingBean   // 没有Jedis这个类才进行装配
    public Jedis jedis(RedisProperties redisProperties) {
        System.out.println("初始化jedis; [" + redisProperties.toString() + "]");
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }


}
