package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.EGender;
import com.apera.aperaserver.model.Gender;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long>, QuerydslPredicateExecutor<Gender>{
    boolean existsByName(EGender name);
}
