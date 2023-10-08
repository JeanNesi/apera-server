package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, QuerydslPredicateExecutor<Pessoa> {
}
