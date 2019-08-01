package com.herokusmallapp.repositories;


import com.herokusmallapp.entity.LoggedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggedUserRepository extends JpaRepository<LoggedUser, Long> {
    LoggedUser findByUsername(String name);
}
