package com.algaworks.algafood.repositories.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	
	//13.14. Implementando consulta com dados agregados de vendas diárias
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		
		/* Implementando essa consulta abaixo com a API Criteria do JPA
		 * 
		 * select date(p.data_criacao) as dataCriacao,
	  			  count(p.id) as totalDeVendas,
      			  sum(p.valor_total) as totalFaturado
		   from pedido p

		   group by date(p.data_criacao)
		 * 
		 * 
		 * 
		 * */
		
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		
		/*Os metodos entre aspas usados nas variaveis iniciadas com 'function'
		 * são especificos do banco MySQL
		 * 
		 * 
		 * */
		
		var functionConvertTimeZoneDataCriacao = builder.function("convert_tz", 
																  Date.class, 
																  root.get("dataCriacao"), 
																  builder.literal("+00:00"), 
																  builder.literal(timeOffset));
		
		var functionDate = builder.function("date", Date.class, functionConvertTimeZoneDataCriacao);
		
		//FILTROS
		
		var predicates = new ArrayList<Predicate>();
		
		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}
		
		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		//========
		
		var selection = builder.construct(VendaDiaria.class, 
										  functionDate,
										  builder.count(root.get("id")),
										  builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDate);
		
		return manager.createQuery(query).getResultList();
	}
}