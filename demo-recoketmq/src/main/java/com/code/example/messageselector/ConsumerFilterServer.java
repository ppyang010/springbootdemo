package com.code.example.messageselector;

import com.code.example.simple.RocketMqUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

public class ConsumerFilterServer {

    public static void main(String[] args) throws InterruptedException, MQClientException, IOException {

        DefaultMQPushConsumer consumer = RocketMqUtil.getDefaultMQPushConsumer("select_consumer_group");
//        String devPath = ConsumerFilterServer.class.getResource("").getPath();
        String devPath = "D:\\work\\code\\gitcode\\springbootdemo\\demo-recoketmq\\src\\main\\java\\com\\code\\example\\messageselector\\MyMessageFilter.java";
        //获取自定义Filt数据
        String filterCode = MixAll.file2String(devPath);

        consumer.subscribe("select_topic", "com.code.example.messageselector.MyMessageFilter", filterCode);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.print(Thread.currentThread().getName() + " Receive New Messages: ");
                for (MessageExt msg : msgs) {
                    System.out.println("content:" + new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("ConsumerFilterServer Started.%n");
    }
}