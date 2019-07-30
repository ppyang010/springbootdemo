package com.code.domain.two;

import com.code.domain.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "t_order")
@Accessors(chain = true)
public class Order extends BaseDomain implements Serializable {
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


    private String username;



}