package com.code.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 * 继承 JpaRepository  表对应的实体类 和 主键
 */
public interface UserTestDao extends JpaRepository<UserTestEntity,Integer> {
    // 自定义的查询  方法名必须按这个格式写 才可以不写方法的实现
    List<UserTestEntity> findByUsername(String name);
}
