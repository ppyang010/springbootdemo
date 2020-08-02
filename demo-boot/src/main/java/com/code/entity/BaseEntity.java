package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableLogic(value = "0", delval = "1")
    private Boolean isDeleted;
}
