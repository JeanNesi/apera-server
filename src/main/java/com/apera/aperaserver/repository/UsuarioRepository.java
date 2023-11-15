package com.apera.aperaserver.repository;

import com.apera.aperaserver.model.Usuario;
import org.hibernate.annotations.RowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {
}
