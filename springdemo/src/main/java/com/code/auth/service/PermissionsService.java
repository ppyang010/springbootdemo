package com.code.auth.service;

import java.util.Set;

/**
 * 权限业务层
 * @author ccy
 */
public interface PermissionsService {
    Set<String> listPermissionsByUsername(String username);
}
