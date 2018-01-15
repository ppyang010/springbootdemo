package com.code.auth.service.impl;

import com.code.auth.service.PermissionsService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 权限业务层
 * @author ccy
 */
@Service
public class PermissionsServiceImpl implements PermissionsService{
    @Override
    public Set<String> listPermissionsByUsername(String username) {
        return null;
    }
}
