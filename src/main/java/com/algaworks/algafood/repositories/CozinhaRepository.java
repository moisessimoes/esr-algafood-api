package com.algaworks.algafood.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.repositories.infrastructure.CustomJpaRepository;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);

}