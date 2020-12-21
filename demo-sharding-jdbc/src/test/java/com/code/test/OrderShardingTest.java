package com.code.test;

import com.code.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.code.mapper")
public class OrderShardingTest {
    @Resource
    OrderService orderService;

    @Test
    public void insert() {

        orderService.insert();
    }

    @Test
    public void select() {
//        Order order1 = orderService.getOrderInfoByOrderId("547544286233624577");
//        System.out.println("------order1:"+order1);

//        orderService.listOrderInfoByUserId(202);

//        orderService.listOrderInfoByUserIdWithItem(202,203);
        orderService.pageOrder(10000,10);

    }

}

