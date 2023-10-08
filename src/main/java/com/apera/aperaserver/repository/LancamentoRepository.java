package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, QuerydslPredicateExecutor<Lancamento> {
}
