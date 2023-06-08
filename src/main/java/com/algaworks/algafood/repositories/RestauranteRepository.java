package com.algaworks.algafood.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repositories.infrastructure.CustomJpaRepository;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	@Query(value = "FROM Restaurante r JOIN r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinhaId);
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
//	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinha);
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
	
	/*
	 * O metodo abaixo está em uma classe de repositorio customizado (RestauranteRepositoryImpl) e, apenas declarando ele aqui, 
	 * o Spring Data JPA consegue pegar e acessar a implementação do repositorio customizado.
	 * 
	 *  Isso é pq o nome do repositorio está com o sulfixo 'Impl' no final.
	 *  
	 *  Mas... Uma forma melhor de fazer é através do uso de interface, por isso, vou deixar esse metodo comentado
	 *  
	 *  e ele vai estar declarado na interface 'RestauranteRepositoryQueries'
	 */
	
	//List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
