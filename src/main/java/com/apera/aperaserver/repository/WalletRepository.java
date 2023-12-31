package com.apera.aperaserver.repository;

import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import com.apera.aperaserver.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>, CustomQuerydslPredicateExecutor<Wallet> {
}
