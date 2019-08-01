package com.herokusmallapp.repositories;

import com.herokusmallapp.entity.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Long> {}
