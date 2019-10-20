
#构建镜像
sudo docker build -t study/rocketmq-namesrv:4.2.0 .


#启动
sudo docker run --name rmq-namesrv \
-p 29876:9876 \
-v /Users/ccy/docker-data/rocketmq/namesrv/logs:/opt/logs \
-v /Users/ccy/docker-data/rocketmq/namesrv/store:/opt/store \
-d study/rocketmq-namesrv:4.2.0