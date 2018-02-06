package com.code.auth.domain;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author caicy
 * @date 2017年12月26日
 * hibernate 实体类
 */
@Getter
@Setter
@Entity
@Table(name = "sys_users")
public class User implements Serializable{
    @Id
    @GeneratedValue
    /**
     * 主键和自增
     */
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private Integer locked;

    public User() {
    }


    public String getCredentialsSalt() {
        return username + salt;
    }
}
