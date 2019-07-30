package com.code.domain.one;

import com.code.domain.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "t_commodity")
public class Commodity extends BaseDomain implements Serializable {

    /**
     * 名称
     */
    private String title;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 价格，单位为分
     */
    private Integer price;




}