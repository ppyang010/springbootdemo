package com.code.auth.service.impl;

import com.code.auth.dao.UserDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.User;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import com.code.auth.service.UserService;
import com.code.auth.support.ModuleUtil;
import org.apache.shiro.codec.CodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    /**
     * 保存用户信息
     *
     * @param user
     */
    @Override
    public void save(User user) {
        //判断用户名是否存在
        Optional.ofNullable(user)
//                .filter(u->u.getId() != null)
                .map(u->userDao.findByUsername(u.getUsername()))
                .ifPresent(u->{
                        throw new CodeException(ExceptionCode.USERNAME_IS_EXIST);
                });
        userDao.save(user);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Integer id){
        User user = userDao.findOne(id);
            return Optional.ofNullable(user)
                    .orElseThrow(CodecException::new);
//                    .orElseThrow(()->{
//                        throw new CodeException(ExceptionCode.USERINFO_IS_NULL);
//                    });
    }

    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public void deleteUserById(Integer id) {
        userDao.delete(id);
    }
}
