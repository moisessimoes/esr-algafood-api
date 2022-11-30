package com.algaworks.algafood.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTranslator {
	
	//13.11. Implementando um conversor de propriedades de ordenação
	
	public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
		
		/*
		 * Esse metodo faz uma 'traducao' dos nomes enviados como parametros da paginação
		 * 
		 * Exemplo: Se for enviado uma ordenação por nome do cliente, ela provavelmente vai vir
		 * 
		 * nomeCliente
		 * 
		 * Logo, esse 'nomeCliente' vai ser 'traduzido' para cliente.nome, pois é o tipo usado na entidade.
		 * 
		 * */
		
		var orders = pageable.getSort().stream()
									   .filter(order -> fieldsMapping.containsKey(order.getProperty()))
									   .map(order -> new Sort.Order(order.getDirection(), fieldsMapping.get(order.getProperty())))
									   .collect(Collectors.toList());
		
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
	}
}