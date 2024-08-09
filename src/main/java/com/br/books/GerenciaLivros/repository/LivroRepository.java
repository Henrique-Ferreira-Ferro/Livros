package com.br.books.GerenciaLivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.books.GerenciaLivros.entities.LivroEntity;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long>{

}
