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
          && sed -i 's#-Xms[0-9]\+[gm]#-Xms256m#' runserver.sh \
          && sed -i 's#-Xmx[0-9]\+[gm]#-Xmx256m#' runserver.sh \
          && sed -i 's#-Xmn[0-9]\+[gm]#-Xmn128m#' runserver.sh \
          && sed -i 's#-XX:MetaspaceSize=[0-9]\+[gm]#-XX:MetaspaceSize=64m#' runserver.sh \
          && sed -i 's#-XX:MaxMetaspaceSize=[0-9]\+[gm]#-XX:MaxMetaspaceSize=128m#' runserver.sh \
          && chmod +x ./mqnamesrv

CMD cd ${ROCKETMQ_HOME}/bin && sh mqnamesrv

EXPOSE 9876

VOLUME ["/opt/logs", "/opt/store"]