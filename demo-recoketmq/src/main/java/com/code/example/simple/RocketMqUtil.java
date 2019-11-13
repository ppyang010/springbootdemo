package com.code.example.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
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
    public static final String NAMESRV_ADDR = "192.168.190.209:29876";
    /**
     * mac home
     */
//    public static final String NAMESRV_ADDR = "192.168.190.103:29876";

    public static DefaultMQProducer producer;

    static {
        producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
//        producer.setNamesrvAddr("129.204.121.60:9876");
        //mac
        producer.setNamesrvAddr("192.168.202.119:29876");
        //win
//        producer.setNamesrvAddr("192.168.190.209:29876");
        //        mac home
//        producer.setNamesrvAddr("192.168.190.103:29876");
        //Launch the instance.
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static DefaultMQProducer getDefaultMQProducer(String  groupname){
        DefaultMQProducer producer = new DefaultMQProducer(groupname);
        // Specify name server addresses.
        producer.setNamesrvAddr(NAMESRV_ADDR);
        return producer;
    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(String groupName){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        // Specify name server addresses.
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        return consumer;

    }

}
