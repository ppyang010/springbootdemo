package com.code.auth.service.impl;

import com.code.auth.dao.RoleDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.Role;
import com.code.auth.service.RoleService;
import com.code.auth.support.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return roles;
    }

    /**
     * 分页获取角色列表
     *
     * @param pageBean
     */
    @Override
    public Page<Role> listRole(PageBean pageBean) {
        PageRequest pageRequest = ModuleUtil.toPageable(pageBean, null);
        Page<Role> roles = roleDao.findAll(pageRequest);
        return roles;
    }
}
