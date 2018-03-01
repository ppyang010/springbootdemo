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
     * 作为user和role 关系的主体 @joinTable 注解代表是主体 只有主体的类 往集合中添加元素后save 会被保存到数据库
     */
    @JoinTable(name="sys_users_roles",
            //当前表
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            //对应表
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    @ManyToMany()
    @JsonIgnore
    private Set<Role> roleSet;


    public String getCredentialsSalt() {
        return username + salt;
    }
}
