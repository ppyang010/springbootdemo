package com.code.example.scheduled;

import com.alibaba.fastjson.JSON;
import com.code.example.simple.RocketMqUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

public class ScheduledMessageProducer {
    /**
     * 发送定时消息
     */
    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = RocketMqUtil.getDefaultMQProducer("SCHEDULED_PRODUCER_GROUP");
        // Launch producer
        producer.start();
        int totalMessagesToSend = 3;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TEST_TOPIC_SCHEDULED", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            //private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
            message.setDelayTimeLevel(2);
            // Send the message
            SendResult sendResult = producer.send(message);
            System.out.println(JSON.toJSONString(sendResult));
            System.out.println("send time = " + System.currentTimeMillis()/1000);
        }

        // Shutdown producer after use.
        producer.shutdown();
    }

}