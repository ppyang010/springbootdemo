package com.code.entity;

public class OrderItem {
    private Integer id;

    private String orderNo;

    private String username;

    private Integer courseId;

    public Integer getId() {
        return id;
    }

    public OrderItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public OrderItem setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OrderItem setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public OrderItem setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }
}