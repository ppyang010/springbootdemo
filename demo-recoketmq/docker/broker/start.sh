sudo docker run --name rmq-broker \
-p 10911:10911 -p 10909:10909 -p 10912:10912 \
-v /Users/ccy/docker-data/rocketmq/broker/logs:/opt/logs \
-v /Users/ccy/docker-data/rocketmq/broker/store:/opt/store \
-v /Users/ccy/docker-data/rocketmq/broker/conf/broker.properties:/opt/broker.properties \
-d study/rocketmq-broker:4.2.0 sh ./bin/mqbroker -c /opt/broker.properties