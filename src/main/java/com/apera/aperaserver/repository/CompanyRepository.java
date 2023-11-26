package com.apera.aperaserver.repository;

import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import com.apera.aperaserver.model.Company;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, CustomQuerydslPredicateExecutor<Company> {
    Optional<Company> findById(BooleanExpression eq);
}
