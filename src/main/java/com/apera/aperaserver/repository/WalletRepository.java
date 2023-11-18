package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>, QuerydslPredicateExecutor<Wallet> {
}
