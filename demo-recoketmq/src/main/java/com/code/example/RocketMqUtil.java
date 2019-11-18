package com.code.example;

import com.code.example.transaction.TransactionListenerImpl;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;

import java.util.concurrent.*;

/**
 * @author ccy
 * @description
 * @time 2019-10-20 17:42
 */
public class RocketMqUtil {
    /**
     * mac
     */
    public static final String NAMESRV_ADDR = "192.168.202.119:29876";
    /**
     * win
     */
//    public static final String NAMESRV_ADDR = "192.168.190.209:29876";
    /**
     * mac 宿舍
     */
//    public static final String NAMESRV_ADDR = "192.168.190.111:29876";


    public static DefaultMQProducer getDefaultMQProducer(String groupname) {
        DefaultMQProducer producer = new DefaultMQProducer(groupname);
        // Specify name server addresses.
        producer.setNamesrvAddr(NAMESRV_ADDR);
        return producer;
    }

    public static DefaultMQProducer getTransactionMQProducer(String groupname) {
        TransactionMQProducer producer = new TransactionMQProducer(groupname);
        // Specify name server addresses.
        producer.setNamesrvAddr(NAMESRV_ADDR);
        TransactionListener transactionListener = new TransactionListenerImpl();
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        return producer;
    }


    public static DefaultMQPushConsumer getDefaultMQPushConsumer(String groupName) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        // Specify name server addresses.
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        return consumer;

    }

}
