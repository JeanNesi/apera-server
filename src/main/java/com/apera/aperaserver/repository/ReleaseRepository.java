package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Release;
import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long>, CustomQuerydslPredicateExecutor<Release> {
}
