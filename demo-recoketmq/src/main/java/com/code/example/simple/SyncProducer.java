package com.code.example.simple;

import com.code.example.RocketMqUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Date;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = RocketMqUtil.getDefaultMQProducer("simple-consumer-group");
        producer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        producer.setVipChannelEnabled(false);
        producer.start();
        System.out.println(new Date());
        for (int i = 0; i < 3; i++) {
            //Create a message instance, specifying topic, tag and message body.
            //创建消息实例 确定topic tag message
            // Topic Tag Message body
            Message msg = new Message("TopicTestC",
                    "TagA","key", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = null;
            try {
                sendResult = producer.send(msg,30000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("%s%n", sendResult);
            System.out.println(new Date());

        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}