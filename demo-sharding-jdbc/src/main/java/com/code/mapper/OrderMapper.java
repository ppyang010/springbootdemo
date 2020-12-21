package com.code.mapper;


import com.code.entity.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectByUserId(Integer id);

    List<Order> listOrderInfoByUserIdWithItem(Integer id1, Integer id2);

    List<Order> pageOrder(int offset, int limit);
}