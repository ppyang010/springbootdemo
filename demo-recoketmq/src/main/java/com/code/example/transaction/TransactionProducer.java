package com.code.example.transaction;

import com.code.example.RocketMqUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class TransactionProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = RocketMqUtil.getTransactionMQProducer("TRANSACTION_PRODUCER_GROUP");
        producer.start();

        for (int i = 0; i < 1; i++){
            Message msg = new Message("TOPIC_TRANSACTION_TEST",
                "TagA",
                "OrderID188",
                "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}