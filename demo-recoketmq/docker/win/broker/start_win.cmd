## 镜像构建命令
docker build -t study/rocketmq-broker:4.2.0 .

## 启动命令  windows 宿主机目录挂载有点问题 使用命令2
docker run --name rmq-broker \
-p 10911:10911 -p 10909:10909 -p 10912:10912 \
-v  //e/docker_data/rocketmq/broker/logs:/opt/logs \
-v //e/docker_data/rocketmq/broker/store:/opt/store \
-v //e/docker_data/rocketmq/broker/conf/broker.properties:/opt/broker.properties \
-d study/rocketmq-broker:4.2.0 sh ./bin/mqbroker -c /opt/broker.properties


## 启动命令  2
docker run --name rmq-broker \
-p 10911:10911 -p 10909:10909 -p 10912:10912 \
-d study/rocketmq-broker:4.2.0 sh ./bin/mqbroker -n 192.168.190.209:29876  -c 192.168.190.209:10911 autoCreateTopicEnable=true

## 进入容器
winpty docker exec -it d00b bash



telnet 192.168.190.209 29876
telnet 192.168.190.209 10909
telnet 192.168.190.209 29876