package com.code.auth.dao;

import com.code.auth.domain.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 权限数据层
 * @author ccy
 */
public interface PermissionsDao extends PagingAndSortingRepository<Permissions,Integer> {
    @Query(value = "SELECT sp.* from sys_permissions sp INNER join sys_roles_permissions srp on sp.id = srp.permission_id \n" +
            "INNER join sys_users_roles sur on srp.role_id = sur.role_id INNER JOIN sys_users su on su.id = sur.user_id\n" +
            "where su.username = ?1", nativeQuery = true)
    List<Permissions> findAllByUsername(String username);

    Permissions findByPermission(String permission);
}
