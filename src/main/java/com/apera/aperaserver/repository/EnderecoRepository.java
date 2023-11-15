package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, QuerydslPredicateExecutor<Endereco> {
}
