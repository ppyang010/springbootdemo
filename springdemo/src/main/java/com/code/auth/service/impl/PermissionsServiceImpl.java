package com.code.auth.service.impl;

import com.code.auth.dao.PermissionsDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.Permissions;
import com.code.auth.service.PermissionsService;
import com.code.auth.support.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限业务层
 * @author ccy
 */
@Service
public class PermissionsServiceImpl implements PermissionsService{
    @Autowired
    PermissionsDao permissionsDao;

    @Override
    public Set<String> listPermissionsByUsername(String username) {
        List<Permissions> list = permissionsDao.findAllByUsername(username);
        return list.stream().map(p -> p.getPermission()).collect(Collectors.toSet());
    }

    /**
     * 分页获取权限列表
     *
     * @param pageBean
     */
    @Override
    public Page<Permissions> listPermission(PageBean pageBean) {
        PageRequest pageRequest = ModuleUtil.toPageable(pageBean, null);
        Page<Permissions> page = permissionsDao.findAll(pageRequest);
        return page;
    }

    @Override
    public void deleteById(int id) {
        permissionsDao.delete(id);
    }
}
