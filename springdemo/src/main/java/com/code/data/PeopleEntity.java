package com.code.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/10/5.
 * hibernate 实体类
 */
@Entity
public class PeopleEntity {
    @Id
    @GeneratedValue
    //主键和自增
    private Integer id;

    private String name;

    private Integer age;

    public PeopleEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
