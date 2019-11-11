
# 参考:https://blog.csdn.net/sinat_28434649/article/details/79295164

#下载镜像
docker pull styletang/rocketmq-console-ng

#创建容器  （运行）
docker run --link rmq-namesrv:rmqnamesrv \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.202.119:29876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 8001:8080 -t styletang/rocketmq-console-ng


## 后台运行
docker run --link rmq-namesrv:rmqnamesrv \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.202.119:29876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 8001:8080 -d styletang/rocketmq-console-ng


localhost:8001


docker run --link rmq-namesrv:rmqnamesrv \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.190.103:29876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 8001:8080 -t styletang/rocketmq-console-ng