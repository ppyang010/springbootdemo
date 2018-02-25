package com.code.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_roles")
public class Role implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    private String description;
    //是否可用
    private Integer available;

    /**
     * 拥有该权限的role列表
     * 作为role和permission 关系的主题
     */
    @JoinTable(name="sys_roles_permissions",
            //当前表
            joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
            //对应表
            inverseJoinColumns={@JoinColumn(name="permission_id", referencedColumnName="id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    //互相引用导致无法序列化 临时忽略
    private Set<Permissions> permissionSet;

}
