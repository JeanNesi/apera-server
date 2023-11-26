package com.apera.aperaserver.repository;

import com.apera.aperaserver.enterprise.CustomQuerydslPredicateExecutor;
import com.apera.aperaserver.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, CustomQuerydslPredicateExecutor<Person> {
}
