# ds0 ds1
CREATE TABLE `t_order_0` (
                             order_no varchar(50) NOT NULL  primary key comment  '订单号',
                             user_id int  NOT NULL default '0' comment '用户id',
                             user_name varchar(50)  NOT NULL default '' comment '用户名',
                             pay_amount int  not null default 0 comment '支付金额',
                             created_time datetime not null default  current_timestamp comment '创建时间',
                             modified_time datetime not null default  current_timestamp comment '修改时间',
                             is_deleted tinyint not null default 0 comment  '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_1` (
                             order_no varchar(50) NOT NULL  primary key comment  '订单号',
                             user_id int  NOT NULL default '0' comment '用户id',
                             user_name varchar(50)  NOT NULL default '' comment '用户名',
                             pay_amount int  not null default 0 comment '支付金额',
                             created_time datetime not null default  current_timestamp comment '创建时间',
                             modified_time datetime not null default  current_timestamp comment '修改时间',
                             is_deleted tinyint not null default 0 comment  '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_order_item_0` (
                                  id int  not null primary key auto_increment comment '自增id',
                                  order_no varchar(50) NOT NULL   comment  '订单号',
                                  user_id int NOT NULL default '0' comment '用户id',
                                  user_name varchar(50)  NOT NULL default '' comment '用户名',
                                  course_id int unsigned not null default '0' comment '课程id',
                                  created_time datetime not null default  current_timestamp comment '创建时间',
                                  modified_time datetime not null default  current_timestamp comment '修改时间',
                                  is_deleted tinyint not null default 0 comment  '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_order_item_1` (
                                  id int  not null primary key auto_increment comment '自增id',
                                  order_no varchar(50) NOT NULL   comment  '订单号',
                                  user_id int NOT NULL default '0' comment '用户id',
                                  user_name varchar(50)  NOT NULL default '' comment '用户名',
                                  course_id int unsigned not null default '0' comment '课程id',
                                  created_time datetime not null default  current_timestamp comment '创建时间',
                                  modified_time datetime not null default  current_timestamp comment '修改时间',
                                  is_deleted tinyint not null default 0 comment  '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_config` (
                            `config_id` int(16) NOT NULL AUTO_INCREMENT,
                            `para_name` varchar(255) DEFAULT NULL,
                            `para_value` varchar(255) DEFAULT NULL,
                            `para_desc` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_info` (
                             `user_id` bigint(128) NOT NULL,
                             `user_name` varchar(45) DEFAULT NULL,
                             `account` varchar(45) NOT NULL,
                             `password` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
