package com.code.auth.domain;


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
@Table(name="sys_permissions")
public class Permissions implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String permission;

    private String description;

    private Integer available;
    /**
     * 拥有该权限的role列表
     * 对应role 中的属性
     * 作为user和role 关系的副主体
     */
    @ManyToMany(mappedBy = "permissionSet")
    private Set<Role> roleSet;

}
