package com.code.service;

import com.code.entity.Order;
import com.code.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class OrderService {
    @Resource
    OrderMapper orderMapper;

    public static Long orderId = 1L;
    public static Long userId = 1L;

    public void insert() {
        for (int i = 1; i <= 10; i++) {
            int random = ThreadLocalRandom.current().nextInt(1000);
            Order order = new Order();

//            order.setOrderNo("orderNo"+random);
            order.setUserId(random);
            order.setPayAmount(1000);
            orderMapper.insert(order);
        }
    }

    public Order getOrderInfoByOrderId(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> listOrderInfoByUserId(Integer id) {
        return orderMapper.selectByUserId(id);
    }

    public List<Order> listOrderInfoByUserIdWithItem(Integer id1, Integer id2) {
        return orderMapper.listOrderInfoByUserIdWithItem(id1, id2);
    }

    public List<Order> pageOrder(int offset, int limit) {
        return orderMapper.pageOrder(offset, limit);
    }
}
