package com.code.auth.service;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2017/12/27.
 * @author ccy
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     */
    User getByUsername(String username);

    /**
     * 获取用户列表
     * @param pageBean
     * @return
     */
    Page<User> listUser(PageBean pageBean);

    /**
     * 保存用户信息
     */
    void save(User user);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getById(Integer id) ;

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    void deleteUserById(Integer id);
}
