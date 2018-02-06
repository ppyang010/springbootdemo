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
}
