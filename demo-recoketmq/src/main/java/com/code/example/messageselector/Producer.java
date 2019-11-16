package com.code.example.messageselector;

import com.code.example.simple.RocketMqUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.Arrays;
import java.util.List;

public class Producer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = RocketMqUtil.getDefaultMQProducer("select_producer_group");
        producer.start();
        List<String> tagList = Arrays.asList("TagA", "TagB", "TagC", "TagD");
        for (int i = 0; i < 8; i++) {
            //Create a message instance, specifying topic, tag and message body.
            //创建消息实例 确定topic tag message
            // Topic Tag Message body
            int index = i % tagList.size();
            String tagStr = tagList.get(index);
            System.out.println("tagStr = " + tagStr);

            Message msg = new Message("select_topic",
                    tagStr, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // Set some properties.
            //设置自定义属性用于consumer sql方式过滤
            msg.putUserProperty("a", String.valueOf(i));
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg, 10000);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}