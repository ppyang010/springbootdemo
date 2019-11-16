package com.code.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = RocketMqUtil.getDefaultMQProducer("group");
        producer.start();
        for (int i = 0; i < 3; i++) {
            //Create a message instance, specifying topic, tag and message body.
            //创建消息实例 确定topic tag message
            // Topic Tag Message body
            Message msg = new Message("TopicTestC",
                    "TagA", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg,10000);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}