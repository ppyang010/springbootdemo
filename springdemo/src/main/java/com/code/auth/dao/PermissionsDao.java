package com.code.auth.dao;

import com.code.auth.domain.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限数据层
 * @author ccy
 */
public interface PermissionsDao extends JpaRepository<Permissions,Integer>{

}
