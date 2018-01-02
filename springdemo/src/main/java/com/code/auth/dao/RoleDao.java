package com.code.auth.dao;

import com.code.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer>{
}
