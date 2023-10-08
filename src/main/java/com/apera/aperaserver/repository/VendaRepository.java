package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.QuantidadeVendaException;
import com.apera.aperaserver.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface VendaRepository extends JpaRepository<Venda, Long>, QuerydslPredicateExecutor<Venda> {
}
