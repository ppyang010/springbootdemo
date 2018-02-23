package com.code.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

}
