use ccy_commodity;
CREATE TABLE t_commodity
(
  id            int unsigned auto_increment comment '自增ID' primary key,
  title         varchar(100) DEFAULT ''                not null comment '名称',
  stock         int unsigned default '0'               not null comment '库存',
  price         int unsigned default '0'               not null comment '价格,单位为分',
  created_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
  modified_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  is_deleted    tinyint      default 0                 not null comment '逻辑删除 0：正常 1：已删除'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  comment ='商品';

use ccy_order;
CREATE TABLE t_order
(
  id            int unsigned auto_increment comment '自增ID' primary key,
  order_no      varchar(100) DEFAULT ''                not null comment '订单号',
  commodity_id  int          DEFAULT '0'               not null comment '商品id',
  num           int unsigned default '0'               not null comment '数量',
  pay_price     int unsigned default '0'               not null comment '支付金额,单位为分',
  created_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
  modified_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  is_deleted    tinyint      default 0                 not null comment '逻辑删除 0：正常 1：已删除'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  comment ='订单表';