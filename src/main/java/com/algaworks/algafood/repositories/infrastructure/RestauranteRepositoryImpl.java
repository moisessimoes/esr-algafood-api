package com.algaworks.algafood.repositories.infrastructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repositories.RestauranteRepository;
import com.algaworks.algafood.repositories.RestauranteRepositoryQueries;
import com.algaworks.algafood.specification.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	@Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		//Consulta JPQL usando o Criteria API
		//OBS: Só vale a pena usar o Criteria API para consultas grandes, pois ele é muito "burocratico"

		//==============================================================================================
		
		//Tornando a consulta com Criteria mais dinamica
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class); //from Restaurante
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%"+nome+"%"));
		}
		
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		
		/*
		 * Uma das formas de converter um ArrayList em um Array em Java é instanciando uma lista vazia dentro do metodo 'toArray()'
		 * */
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
		
		return query.getResultList();
		
		
		
		//==============================================================================================
		
		//Adicionando clausula 'where' com o criteria
		
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//		Root<Restaurante> root = criteria.from(Restaurante.class); //from Restaurante
//		
//		Predicate nomePredicate = builder.like(root.get("nome"), "%"+nome+"%");
//		
//		Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
//		Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
//		
//		criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);
//		
//		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
//		
//		return query.getResultList();
		
		//===============================================================================================
		
		/*
		 * Consulta JPQL dinamica
		 * 
		 * 
		 * var jpql = new StringBuilder();
		
		var parametros = new HashMap<String, Object>();
		
		jpql.append("FROM Restaurante WHERE 0 = 0 ");
		
		if (StringUtils.hasLength(nome)) {
			jpql.append("AND nome LIKE :nome ");
			parametros.put("nome", "%"+nome+"%");
		}
		
		if (taxaFreteInicial != null) {
			jpql.append("AND taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append("AND taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		return query.getResultList(); */
	}
	
	
	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
	}
}