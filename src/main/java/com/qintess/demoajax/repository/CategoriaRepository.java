package com.qintess.demoajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.demoajax.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
