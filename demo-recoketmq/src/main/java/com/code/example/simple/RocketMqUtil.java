package com.code.example.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author ccy
 * @description
 * @time 2019-10-20 17:42
 */
public class RocketMqUtil {
    /**
     * mac
     */
//    public static final String NAMESRV_ADDR = "192.168.202.119:29876";
    /**
     * win
     */
//    public static final String NAMESRV_ADDR = "192.168.190.209:29876";
    /**
     * mac 宿舍
     */
    public static final String NAMESRV_ADDR = "192.168.190.111:29876";


    public static DefaultMQProducer getDefaultMQProducer(String groupname) {
        DefaultMQProducer producer = new DefaultMQProducer(groupname);
        // Specify name server addresses.
        producer.setNamesrvAddr(NAMESRV_ADDR);
        return producer;
    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(String groupName) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        // Specify name server addresses.
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        return consumer;

    }

}
