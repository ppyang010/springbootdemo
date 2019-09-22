## 镜像构建命令
docker inspect study/rocketmq-namesrv:4.2.0

## 启动命令
docker run  --name rmq-namesrv \
-p 29876:9876 \
-v //e/docker_data/rocketmq/namesrv/logs:/opt/logs \
-v //e/docker_data/rocketmq/namesrv/store:/opt/store \
-d study/rocketmq-namesrv:4.2.0


## 启动命令2
docker run  --name rmq-namesrv \
-p 29876:9876 \
-d study/rocketmq-namesrv:4.2.0

## 进入容器
winpty docker exec -it d00b bash


## windows 宿主机目录挂载问题处理命令
docker run -v E:\docker_data:/data alpine ls /data