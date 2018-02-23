package com.code.auth.service;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.Role;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * 角色业务层
 * @author ccy
 */
public interface RoleService {
    Set<String> listRolesByUsername(String username);

    /**
     * 分页获取角色列表
     * @param pageBean
     */
    Page<Role> listRole(PageBean pageBean);

    /**
     * 保存
     * @param role
     */
    void save(Role role);

    void deleteRoleById(int id);

    Role getRoleById(int id);

    /**
     * 更新角色可用状态 为另一状态
     * @param id
     */
    void changeAvailable(int id);

    /**
     *
     * @param roleName
     * @return
     */
    Role getRoleByRoleName(String roleName);
}
