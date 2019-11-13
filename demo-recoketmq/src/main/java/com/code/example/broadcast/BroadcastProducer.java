package com.code.example.broadcast;

import com.code.example.simple.RocketMqUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class BroadcastProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = RocketMqUtil.getDefaultMQProducer("BROADCAST_PRODUCER_GROUP");
        producer.start();

        for (int i = 0; i < 1; i++){
            Message msg = new Message("TOPIC_BROADCAST_TEST",
                "TagA",
                "OrderID188",
                "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}