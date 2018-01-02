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
@Table(name="sys_roles")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    private String description;

    private Integer available;

}
