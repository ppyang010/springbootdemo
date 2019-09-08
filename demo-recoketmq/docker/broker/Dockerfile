#Dockerfile文件内容
#RocketMQ以来Java环境，因此基于Java镜像进行构建
#修改启动参数-Xms8g -Xmx8g -Xmn4g为-Xms256m -Xmx256m -Xmn128m，虚拟机内存较小
#10911：消息监听端口
#10912：主从同步端口

FROM java:8

ENV ROCKETMQ_VERSION 4.2.0

ENV ROCKETMQ_HOME  /opt/rocketmq-${ROCKETMQ_VERSION}
ENV JAVA_OPT " -Duser.home=/opt"

WORKDIR  ${ROCKETMQ_HOME}

RUN mkdir -p /opt/logs /opt/store

RUN curl https://dist.apache.org/repos/dist/release/rocketmq/${ROCKETMQ_VERSION}/rocketmq-all-${ROCKETMQ_VERSION}-bin-release.zip -o rocketmq.zip \
          && unzip rocketmq.zip \
          && rm rocketmq.zip \
          && cd ${ROCKETMQ_HOME}/bin \
          && sed -i 's#-Xms[0-9]\+[gm]#-Xms256m#' runbroker.sh \
          && sed -i 's#-Xmx[0-9]\+[gm]#-Xmx256m#' runbroker.sh \
          && sed -i 's#-Xmn[0-9]\+[gm]#-Xmn128m#' runbroker.sh \
          && chmod +x ./mqbroker

EXPOSE 10909 10911 10912

VOLUME ["/opt/logs", "/opt/store"]

CMD cd ${ROCKETMQ_HOME}/bin && sh mqbroker