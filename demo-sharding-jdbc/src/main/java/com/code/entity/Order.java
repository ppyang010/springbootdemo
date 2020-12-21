package com.code.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderNo;

    private Integer userId;

    private Integer payAmount;


    public String getOrderNo() {
        return orderNo;
    }

    public Order setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }


    public Integer getUserId() {
        return userId;
    }

    public Order setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public Order setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
        return this;
    }


}