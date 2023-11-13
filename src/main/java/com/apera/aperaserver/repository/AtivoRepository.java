package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long>, QuerydslPredicateExecutor<Ativo> {
}
