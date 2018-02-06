package com.code.auth.service.impl;

import com.code.auth.dao.UserDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import com.code.auth.support.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /**
     * 获取用户列表
     *
     * @param pageBean
     * @return
     */
    @Override
    public Page<User> listUser(PageBean pageBean) {
        PageRequest pageRequest = ModuleUtil.toPageable(pageBean, null);
        Page<User> list = userDao.findAll(pageRequest);
        return list;
    }
}
