package com.apera.aperaserver.repository;

import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import com.apera.aperaserver.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, CustomQuerydslPredicateExecutor<Address> {
}
