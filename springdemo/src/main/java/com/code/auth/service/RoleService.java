package com.code.auth.service;

import java.util.Set;

/**
 * 角色业务层
 * @author ccy
 */
public interface RoleService {
    Set<String> listRolesByUsername(String username);
}
