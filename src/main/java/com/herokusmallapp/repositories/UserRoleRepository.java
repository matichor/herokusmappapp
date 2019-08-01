package com.herokusmallapp.repositories;


import com.herokusmallapp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
