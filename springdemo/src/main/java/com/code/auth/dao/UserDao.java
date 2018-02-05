package com.code.auth.dao;

import com.code.auth.domain.User;
import com.code.data.UserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 * @author ccy
 * 继承 JpaRepository  表对应的实体类 和 主键
 */
public interface UserDao extends PagingAndSortingRepository<User,Integer> {
    /**
     *自定义的查询  方法名必须按这个格式写 才可以不写方法的实现
     * @param name
     * @return
     */
    User findByUsername(String name);
}
