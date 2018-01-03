package com.code.auth.dao;

import com.code.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleDao extends JpaRepository<Role,Integer>{
    @Query
    Set<String> findAllByUsername(String username);
}
