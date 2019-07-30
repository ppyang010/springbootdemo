package com.code.service;

import cn.hutool.core.util.IdUtil;
import com.code.dao.one.CommodityRepository;
import com.code.dao.two.OrderRepository;
import com.code.domain.one.Commodity;
import com.code.domain.two.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ccy
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Commodity addGoods(String title, Integer price, Integer stock) {

        Commodity commodity = new Commodity();
        commodity.setPrice(price);
        commodity.setStock(stock);
        commodity.setTitle(title);
        return commodityRepository.save(commodity);

    }

    public void create(String username, Integer commodityId, Integer num){
        decrStock(commodityId, num);
        createOrder(username, commodityId, num);
    }

    public void decrStock(Integer commodityId, Integer num) {
        int decr = commodityRepository.decrStock(commodityId, num);
        log.info("decr={}",decr);
    }

    public void createOrder(String username, Integer commodityId, Integer num) {
        Optional<Commodity> byId = commodityRepository.findById(commodityId);
        Commodity commodity = byId.get();
        Order order = new Order()
                .setCommodityId(commodityId)
                .setNum(num)
                .setPayPrice(commodity.getPrice() * num)
                .setUsername(username)
                .setOrderNo(IdUtil.randomUUID());
        orderRepository.save(order);
    }
}
