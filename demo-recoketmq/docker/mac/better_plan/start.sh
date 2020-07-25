#!/usr/bin/env bash
#资料 整合的比较好的一个镜像
https://hub.docker.com/r/foxiswho/rocketmq
# 启动nameserver
docker run -d -v $(pwd)/logs:/home/rocketmq/logs \
      --name rmqnamesrv \
     -e "JAVA_OPT_EXT=-Xms512M -Xmx512M -Xmn128m" \
      -p 9876:9876 \
      foxiswho/rocketmq:4.7.0 \
      sh mqnamesrv


# 启动 broker  前台启动
# $(pwd)为执行命令所在的当前目录
#$(pwd)/conf 这个目录可以放自定义的配置文件broker.conf  会映射到容器中
docker run -it  -v $(pwd)/logs:/home/rocketmq/logs -v $(pwd)/store:/home/rocketmq/store \
      -v $(pwd)/conf:/home/rocketmq/conf \
      --name rmqbroker \
      -e "NAMESRV_ADDR=rmqnamesrv:9876" \
      -e "JAVA_OPT_EXT=-Xms512M -Xmx512M -Xmn128m" \
      -p 10911:10911 -p 10912:10912 -p 10909:10909 \
      foxiswho/rocketmq:4.7.0 \
      sh mqbroker -c /home/rocketmq/conf/broker.conf



#启动 broker  后台启动
docker run -d  -v $(pwd)/logs:/home/rocketmq/logs -v $(pwd)/store:/home/rocketmq/store \
      -v $(pwd)/conf:/home/rocketmq/conf \
      --name rmqbroker \
      -e "NAMESRV_ADDR=rmqnamesrv:9876" \
      -e "JAVA_OPT_EXT=-Xms512M -Xmx512M -Xmn128m" \
      -p 10911:10911 -p 10912:10912 -p 10909:10909 \
      foxiswho/rocketmq:4.7.0 \
      sh mqbroker -c /home/rocketmq/conf/broker.conf



docker run --name rmqconsole --link rmqnamesrv:namesrv \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=rmqnamesrv:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 8180:8080 -t styletang/rocketmq-console-ng

#启动 控制台
docker run --name rmqconsole --link rmqnamesrv:namesrv \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.202.119:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 8180:8080 -t styletang/rocketmq-console-ng


# 进入容器
docker exec -it xxid  bash