package com.code.auth.service;

/**
 * Created by Administrator on 2017/12/27.
 * @author ccy
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     */
    void getUserByUsername(String username);
}
