create table order_0
(
    id         int unsigned auto_increment comment '主键'
        primary key,
    order_no   varchar(500) default 'default' not null comment '订单号',
    username   varchar(500) default 'default' not null comment '用户名',
    pay_amount int          default 0         not null comment '支付金额'
) comment 'order' default charset = utf8mb4;