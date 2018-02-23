package com.code.auth.service;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.Permissions;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * 权限业务层
 * @author ccy
 */
public interface PermissionsService {
    Set<String> listPermissionsByUsername(String username);

    /**
     * 分页获取权限列表
     * @param pageBean
     */
    Page<Permissions> listPermission(PageBean pageBean);

    void deleteById(int id);
}
