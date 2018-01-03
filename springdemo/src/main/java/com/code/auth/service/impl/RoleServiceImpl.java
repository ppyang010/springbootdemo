package com.code.auth.service.impl;

import com.code.auth.dao.RoleDao;
import com.code.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色业务层
 * @author ccy
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;
    /**
     * 根据用户名获取用户角色集合
     * @param username
     * @return
     */
    @Override
    public Set<String> listRolesByUsername(String username) {
        Set<String> roles = roleDao.findAllByUsername(username);
        return null;
    }
}
