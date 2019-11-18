package com.code.example.transaction;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.code.example.RocketMqUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.HashMap;

public class TransactionProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = RocketMqUtil.getTransactionMQProducer("TRANSACTION_PRODUCER_GROUP");
        producer.start();

        for (int i = 0; i < 5; i++) {
            String orderNo = RandomUtil.randomNumbers(10);
            HashMap<String, Object> map = new HashMap<>();
            map.put("orderNo", orderNo);
            String msgStr = JSON.toJSONString(map);
            Message msg = new Message("TOPIC_TRANSACTION_TEST",
                    "TagA",
                    "OrderID",
                    msgStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.sendMessageInTransaction(msg, null);
            System.out.printf("%s%n", sendResult);
            Thread.sleep(1000);
        }
//        producer.shutdown();
    }
}