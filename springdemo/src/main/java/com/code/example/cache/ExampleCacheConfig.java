package com.code.example.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * 其中proxyTargetClass=true表示：当需要代理的类是一个接口或者是一个动态生成的代理类时使用JdkProxy代理；而当要代理的类是一个具体类时，使用cglib来代理。假如不设置该属性，则默认使用JdkProxy代理，而JdkProxy能够代理的类必须实现接口，因此如果想要一个没实现接口的类被代理，就必须设置proxyTargetClass = true来使用cglib完成代理。
 *
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class ExampleCacheConfig {

    @Bean("simpleCacheManager")
    public CacheManager simpleCacheManager(){
        System.out.println("simpleCacheManager bean 声明!!!");
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<Cache>();
        //name 对应     @Cacheable("simpleCache1") 注解中的cacheName值
        //@Cacheable 中设置的cacheName 对应会调用manager的getCache方法  simpleCacheManager 没有提供当cacheName为空时new一个新的cache
        ConcurrentMapCache cache1 = new ConcurrentMapCache("simpleCache1");
        ConcurrentMapCache cache2 = new ConcurrentMapCache("simpleCache2");
        caches.add(cache1);
        caches.add(cache2);
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    @Bean("hutoolCacheManager")
    @Primary
    public CacheManager hutoolCacheManager(){
        return new HutoolCacheManager();
    }
}
