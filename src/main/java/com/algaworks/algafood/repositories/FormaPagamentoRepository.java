package com.algaworks.algafood.repositories;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	@Query(value = "SELECT max(dataAtualizacao) FROM FormaPagamento")
	OffsetDateTime getLastUpdate();
	
	@Query(value = "SELECT dataAtualizacao FROM FormaPagamento WHERE id = :formaPagamentoId")
	OffsetDateTime getLastUpdateById(Long formaPagamentoId);

}