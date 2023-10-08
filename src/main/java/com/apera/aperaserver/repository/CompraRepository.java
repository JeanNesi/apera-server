package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CompraRepository extends JpaRepository<Compra, Long>, QuerydslPredicateExecutor<Compra> {
}
