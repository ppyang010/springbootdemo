package com.code.example.demo;

import cn.hutool.json.JSONUtil;
import com.code.example.RocketMqUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumerB {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        DefaultMQPushConsumer consumer = RocketMqUtil.getDefaultMQPushConsumer("simple-consumer-group-b");
        // Subscribe one more more topics to consume.
        consumer.subscribe("TestTopic", "TagA");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s ConsumerB Receive New Messages: %n", Thread.currentThread().getName());
                List<String> collect = msgs.stream().map(MessageExt::getMsgId).collect(Collectors.toList());
                System.out.println(JSONUtil.parse(collect));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("ConsumerB Started.%n");
    }
}