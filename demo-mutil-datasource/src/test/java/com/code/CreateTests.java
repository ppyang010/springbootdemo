package com.code;

import com.code.domain.one.Commodity;
import com.code.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ccy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void addGoods(){
        Commodity goods = orderService.addGoods("ccy02", 10000, 10);
    }

    @Test
    public void create(){
        orderService.create("ccy",1,1);
    }
}
