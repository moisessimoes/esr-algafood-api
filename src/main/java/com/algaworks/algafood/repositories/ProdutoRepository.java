package com.algaworks.algafood.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	
	List<Produto> findTodosByRestaurante(Restaurante restaurante);
	
	@Query(value = "FROM Produto p WHERE p.ativo = true AND p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	
	@Query(value = "FROM Produto WHERE restaurante.id = :restauranteId AND id = :produtoId")
	Optional<Produto> findById(Long restauranteId, Long produtoId);
	
	@Query(value = "SELECT f FROM FotoProduto f JOIN f.produto p WHERE p.restaurante.id = :restauranteId AND f.produto.id = :produtoId")
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);

}