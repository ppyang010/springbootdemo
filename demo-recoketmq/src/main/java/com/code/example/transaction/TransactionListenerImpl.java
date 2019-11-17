package com.code.example.transaction;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;


public class TransactionListenerImpl implements TransactionListener {

    private ConcurrentHashMap<String, JSONObject> localTrans = new ConcurrentHashMap<>();


    /**
     * 用于提交本地事务
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        StaticLog.info("=========本地事务开始执行=============");
        String message = new String(msg.getBody());
        JSONObject jsonObject = JSONObject.parseObject(message);
        String orderNo = jsonObject.getString("orderNo");
        //模拟执行本地事务begin=======
        /**
         * 本地事务执行会有三种可能
         * 1、commit 成功
         * 2、Rollback 失败
         * 3、网络等原因服务宕机收不到返回结果
         */
        StaticLog.info("本地事务执行参数,orderNo={}", orderNo);
//        int result = produceOrderService.save(userId, productId, total);
        int result = mockSaveResult(msg.getTransactionId(), orderNo);
        //模拟执行本地事务end========
        //TODO 实际开发下面不需要我们手动返回，而是根据本地事务执行结果自动返回
        //1、二次确认消息，然后消费者可以消费
        if (result == 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        //2、回滚消息，Broker端会删除半消息
        if (result == 1) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        //3、Broker端会进行回查消息
        if (result == 2) {
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 用于事务回查
     * <p>
     * executeLocalTransaction（） 提交的二次确认最终未到达 MQ Server，经过固定时间后 MQ Server 将对该消息发起消息回查。
     * 或者 executeLocalTransaction（） 返回LocalTransactionState.UNKNOW 也会调用回 查接口被调用
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        StaticLog.info("==========回查接口=========");
//        String key = msg.getKeys();
        JSONObject jsonObject = localTrans.get(msg.getTransactionId());
        Integer status = jsonObject.getInteger("status");
        //TODO 1、必须根据key先去检查本地事务消息是否完成。
        /**
         * 因为有种情况就是：上面本地事务执行成功了，但是return LocalTransactionState.COMMIT_MESSAG的时候
         * 服务挂了，那么最终 Brock还未收到消息的二次确定，还是个半消息 ，所以当重新启动的时候还是回调这个回调接口。
         * 如果不先查询上面本地事务的执行情况 直接在执行本地事务，那么就相当于成功执行了两次本地事务了。
         */
        // TODO 2、这里返回要么commit 要么rollback。没有必要在返回 UNKNOW
        return LocalTransactionState.COMMIT_MESSAGE;
    }


    private int mockSaveResult(String transactionId, String orderNo) {
        int res = RandomUtil.randomInt(0, 2);
        StaticLog.info("random result = {}", res);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", orderNo);
        jsonObject.put("status", res);
        localTrans.put(transactionId, jsonObject);
        return res;
    }
}
