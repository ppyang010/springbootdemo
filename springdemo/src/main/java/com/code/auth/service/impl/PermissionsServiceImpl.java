package com.code.auth.service.impl;

import com.code.auth.dao.PermissionsDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.Permissions;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import com.code.auth.service.PermissionsService;
import com.code.auth.support.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    /**
     * 添加and更新
     *
     * @param permissions
     */
    @Override
    public void save(Permissions permissions) {
        Optional.ofNullable(permissions)
            .map(p->permissionsDao.findByPermission(permissions.getPermission()))
            .filter(p->!p.getId().equals(permissions.getId()))
            .ifPresent(p-> {
                throw new CodeException(ExceptionCode.PERMISSIONS_IS_EXIST);
            });
        permissionsDao.save(permissions);

    }

    @Override
    public Permissions findPermissionById(int id) {
        return Optional.ofNullable(permissionsDao.findOne(id))
                .orElseThrow(CodeException::infoIsNull);
    }

    /**
     * 更新角色可用状态 为另一状态
     *
     * @param id
     */
    @Override
    public void changeAvailable(Integer id) {
        Permissions permissions = permissionsDao.findOne(id);
        Optional.ofNullable(permissions).ifPresent((p)->{
            p.setAvailable(p.getAvailable().equals(1) ? 0 : 1);
            permissionsDao.save(p);
        });
    }
}
