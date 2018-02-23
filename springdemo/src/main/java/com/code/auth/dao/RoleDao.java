package com.code.auth.dao;

import com.code.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface RoleDao extends PagingAndSortingRepository<Role,Integer> {
    @Query(value = "SELECT sr.* from sys_roles sr INNER join sys_users_roles sur on sr.id = sur.role_id INNER JOIN sys_users su on su.id = sur.user_id\n" +
            "where su.username = ?1",nativeQuery = true)
    Set<String> findAllByUsername(String username);

    Role findByRole(String roleName);
}
