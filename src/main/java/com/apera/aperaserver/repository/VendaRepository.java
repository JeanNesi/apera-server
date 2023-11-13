package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>, CustomQuerydslPredicateExecutor<Venda> {
}
