###写在前面
产出 code + 笔记(教程或者网上资料)

###todo  

4.spel 练习  可以考虑直接实现个注解注解参数支持使用spel表达式 (如解析获取app-name)  
6 spring eureka 实践 各个配置的(使用或者文档或者是提供的接口) 
7 负载均衡  
9 json 序列化反序列化 工具类(mapper util) 复杂对像  泛型对像  
10 阿波罗 分布式配置  
11 分布式调度  

13 表单重复提交的方案  
14 @value  
15 loghandle 
16 限流工具类  提供util 和 注解两个版本  使用guava 和缓存两个方案  
17 分布式任务调度 e-job  
18 sso jwt 服务
19 使用AQS 机制实现自己的东西
22 flume   

24 retry任务重试机制(异步任务) guava 和spring retry方案   
25 sentry
26 spring 线程池  
27 多线程遍历集合
28 字符串模板(beelt,sf4j)  
29 分布式事务  
30 guava cache(异步刷新) +spring cache manager 


###done
5.spring gateway 实践使用  
8 日志 框架 彩色日志  
19 自己用对列实现一个生产消费方式的 code:QueueProducerConsumerDemo wiz:﻿java快速提纲 queue  
1.feign 尝试 请求和接受时间  code:DateParamController  
12 全局id 生成 code:gid-starter  
2.feign 尝试 接受文件 返回参数是文件  code:InMemoryMultipartFile
21 spring cache manage 自定义实现 能使用spring提供的缓存注解 code:com.code.example.cache  wiz:﻿spring cache
3.设置自己的httpclient 客户端  设置连接池, 文件上传, 日期参数   code:com.code.example.httpclient
20 spring 的 ResponseEntity 和 RequestEntity  code:com.code.example.httpclient.RequestController wiz:﻿spring requestEntity 和 responseEntity

27 spring Mvc和 RestTemplate中的Interceptor  HandlerInterceptor和ClientHttpRequestInterceptor wiz: ﻿spring 拦截器

26 spring web(RestTemplate和Mvc) 中的HttpMessageConverter code:DownLoadHTTPMessageConverter    wiz:SpringBoot 消息转换器 HttpMessageConverter 

23 request body 重复读  code:BodyRepeatReadFilter  BodyRepeatReadHttpServletRequestWrapper  wiz:﻿java web request inputstream 重复读问题