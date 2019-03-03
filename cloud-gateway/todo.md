1 编写笔记.predicate,filter,  ok
2 编写demo不同location 转发不同请求  自带的功能和自定义的功能都进行尝试 熔断功能 ok  
3 在全局filter中实现限流 模拟统一登录校验和接口签名 这部分包含笔记 
3.1 限流  ok  
3.2 登录token   sso一起处理 
3.3 接口签名      ok  
4 服务注册与发现  转发到注册中心上有的服务上去   ok  
5 路由信息配置到配置中心

done  
1 基本predicate和filter demo 
2 熔断功能 
3 使用eureka gateway 请求转发到注册中心其他服务
4 自定义的gateway filter /gateway filter factory 自定义  global filter 
5 接口签名
6 限流  