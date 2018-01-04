package com.code.auth.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="sys_permissions")
public class Permissions {
    @Id
    @GeneratedValue
    private Integer id;

    private String permission;

    private String description;

    private Integer available;

}
