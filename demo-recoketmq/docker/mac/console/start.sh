
# 参考:https://blog.csdn.net/sinat_28434649/article/details/79295164

docker run --link rmq-namesrv:rmqnamesrv  -e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.202.119:29876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8001:8080 -t styletang/rocketmq-console-ng
