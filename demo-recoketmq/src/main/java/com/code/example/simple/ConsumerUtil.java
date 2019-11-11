package com.code.example.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

public class ConsumerUtil {

    public static DefaultMQPushConsumer consumer;

    static {
        // Instantiate with specified consumer group name.
        consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
//        consumer.setNamesrvAddr("129.204.121.60:9876");
        //mac
        consumer.setNamesrvAddr("192.168.202.119:29876");
        //win
//        consumer.setNamesrvAddr("192.168.190.209:29876");
//        mac home
//        consumer.setNamesrvAddr("192.168.190.103:29876");
    }
}
