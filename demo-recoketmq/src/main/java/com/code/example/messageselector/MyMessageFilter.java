package com.code.example.messageselector;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;



public class MyMessageFilter implements MessageFilter {

    @Override
    public boolean match(MessageExt msg, FilterContext context) {
        String property = msg.getUserProperty("SequenceId");
        if (property != null) {
            int id = Integer.parseInt(property);
            if ((id % 2) == 0) {
                return true;
            }
        }
        return false;
    }
}
