package com.code.data;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author caicy
 * @date 2017/10/5
 * hibernate 实体类
 */
@Getter
@Setter
@Entity
@Table(name = "sys_users")
public class UserTestEntity {
    @Id
    @GeneratedValue
    //主键和自增
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private Integer locked;

    public UserTestEntity() {
    }


}
