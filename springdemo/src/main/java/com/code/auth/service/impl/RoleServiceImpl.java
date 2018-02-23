package com.code.auth.service.impl;

import com.code.auth.dao.RoleDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.Role;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import com.code.auth.service.RoleService;
import com.code.auth.support.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    /**
     * 保存
     *
     * @param role
     */
    @Override
    public void save(Role role) {
        Optional.ofNullable(role)
        .map(r->roleDao.findByRole(role.getRole()))
        .ifPresent(r-> new CodeException(ExceptionCode.ROLENAME_IS_EXIST));

        role.setAvailable(role.getAvailable() == 0 ? 0 : 1);
        roleDao.save(role);
    }

    @Override
    public void deleteRoleById(int id) {
        roleDao.delete(id);
    }

    @Override
    public Role getRoleById(int id) {
        return Optional.ofNullable(roleDao.findOne(id))
                .orElseThrow(CodeException::infoIsNull);
    }

    /**
     * 更新角色可用状态 为另一状态
     *
     */
    @Override
    public void changeAvailable(int id) {
        Role role = roleDao.findOne(id);
        Optional.ofNullable(role).ifPresent((r)->{
            r.setAvailable(r.getAvailable().equals(1) ? 0 : 1);
        });

    }

    /**
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleByRoleName(String roleName) {
        Role r =roleDao.findByRole(roleName);
        return r;
    }
}
