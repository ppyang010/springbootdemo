## 镜像构建命令
docker build -t study/rocketmq-broker:4.2.0 .

## 启动命令
docker run --name rmq-broker \
-p 10911:10911 -p 10909:10909 -p 10912:10912 \
-v  //e/docker_data/rocketmq/broker/logs:/opt/logs \
-v //e/docker_data/rocketmq/broker/store:/opt/store \
-v //e/docker_data/rocketmq/broker/conf/broker.properties:/opt/broker.properties \
-d study/rocketmq-broker:4.2.0 sh ./bin/mqbroker -c ../broker.properties


## windows 上启动 指定配置文件时使用绝对路径会报错 之前一直启动不起来就是这个问题

## 启动命令2 使用自带的conf启动
docker run --name rmq-broker \
-p 10911:10911 -p 10909:10909 -p 10912:10912 \
-v  //e/docker_data/rocketmq/broker/logs:/opt/logs \
-v //e/docker_data/rocketmq/broker/store:/opt/store \
-d study/rocketmq-broker:4.2.0 sh ./bin/mqbroker -c ./conf/broker.conf






## 进入容器
winpty docker exec -it d00b bash



telnet 192.168.190.209 29876
telnet 192.168.190.209 10909
telnet 192.168.190.209 29876


启动失败的时候用到的命令
实时查看docker日志：$ sudo docker logs -f -t --tail 100 s12
实时查看docker容器名为s12的最后10行日志


