package com.algaworks.algafood.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

public class RestauranteSpecs {
	
	//Fabrica de Specifications de Restaurante
	
	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO); 
	}
	
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%"+nome+"%"); 
	}
}