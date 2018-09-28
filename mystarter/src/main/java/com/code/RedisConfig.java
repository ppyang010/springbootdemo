package com.code;

import com.code.domain.HelloProperties;
import com.code.domain.RedisPoolProperties;
import com.code.domain.RedisProperties;
import com.code.service.HelloService;
import com.code.service.HelloServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration

@EnableConfigurationProperties({RedisProperties.class, RedisPoolProperties.class})
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (
                //存在配置前缀redis
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

    @Autowired
    private RedisPoolProperties redisPoolProperties;


    /**
     * JedisPoolConfig 实例化
     *
     * @param redisPoolProperties 配置信息
     * @return 返回JedisPoolConfig
     */
    @Bean
    @ConditionalOnMissingBean
    @Order
    public JedisPoolConfig jedisPoolConfig(RedisPoolProperties redisPoolProperties) {
        System.out.println("初始化JedisPoolConfig实例;[" + redisPoolProperties.toString() + "]");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        BeanUtils.copyProperties(redisPoolProperties, jedisPoolConfig);
        return jedisPoolConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig, RedisProperties redisProperties) {
        System.out.println("初始化jedisPool实例;[" + redisProperties.toString() + "]");
        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort());
    }


//   单个jedis 连接
//    @Bean
//    @ConditionalOnMissingBean   // 没有Jedis这个类才进行装配
//    public Jedis jedis(RedisProperties redisProperties) {
//        System.out.println("初始化jedis; [" + redisProperties.toString() + "]");
//        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
//    }


}
