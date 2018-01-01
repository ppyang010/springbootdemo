package com.code.auth.service.impl;

import com.code.auth.dao.UserDao;
import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/27.
 * @author ccy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     */
    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
