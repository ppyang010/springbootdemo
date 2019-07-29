package com.code.domain.one;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "t_commodity")
public class Commodity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
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