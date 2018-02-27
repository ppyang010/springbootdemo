package com.code.auth.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
    /**
     * 用户拥有的角色列表
     * 作为user和role 关系的副主体
     */

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnore
    private Set<Role> roleSet;


    public String getCredentialsSalt() {
        return username + salt;
    }
}
