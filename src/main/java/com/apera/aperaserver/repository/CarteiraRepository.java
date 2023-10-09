package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long>, QuerydslPredicateExecutor<Carteira> {
}
