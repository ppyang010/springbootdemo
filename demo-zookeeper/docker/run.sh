# 客户端使用参考
https://blog.csdn.net/qq_26641781/article/details/80886831
# 集群搭建参考
https://blog.csdn.net/mynameissls/article/details/81561975

## 下载镜像
docker search zookeeper
docker pull zookeeper


# docker run 新建并启动容器
docker run --name zookeeper -it -p 2181:2181 -p 2888:2888 -p 3888:3888 zookeeper

docker run --name zookeeper -d -p 2181:2181 -p 2888:2888 -p 3888:3888 zookeeper

# 2181 端口号时 zookeeper client 端口
# 2888端口号是zookeeper服务之间通信的端口
# 3888端口是zookeeper与其他应用程序通信的端口


# 使用 ZK 命令行客户端连接 ZK
docker run -it --rm --link zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper
# 启动一个 zookeeper 镜像, 并运行这个镜像内的 zkCli.sh 命令, 命令参数是 "-server zookeeper"
# 将我们先前启动的名为 zookeeper2181 的容器连接(link) 到我们新建的这个容器上, 并将其主机名命名为 zookeeper
# 当我们执行了这个命令后, 就可以像正常使用 ZK 命令行客户端一样操作 ZK 服务了.


