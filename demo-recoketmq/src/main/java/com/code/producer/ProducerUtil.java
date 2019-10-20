package com.code.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author ccy
 * @description
 * @time 2019-10-20 17:42
 */
public class ProducerUtil {
    public static DefaultMQProducer producer;

    static {
        producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
//        producer.setNamesrvAddr("129.204.121.60:9876");
        //mac
//        producer.setNamesrvAddr("192.168.202.119:29876");
        //win
//        producer.setNamesrvAddr("192.168.190.209:29876");
        //        mac home
        producer.setNamesrvAddr("127.0.0.1:29876");
        //Launch the instance.
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }



}
