package com.code.domain.two;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 商品id
    */
    private Integer commodityId;

    /**
    * 数量
    */
    private Integer num;

    /**
    * 支付金额，单位为分
    */
    private Integer payPrice;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 修改时间
    */
    private Date modifiedTime;

    /**
    * 逻辑删除 0：正常 1：已删除
    */
    private Integer isDeleted;


}