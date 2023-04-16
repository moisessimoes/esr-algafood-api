package com.algaworks.algafood.api.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("PedidosModel")
public class PedidosModelOpenApi { //19.45. Desafio: corrigindo a documentação dos endpoint de pedidos (paginação)
	
	private PedidoEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@ApiModel("PedidosEmbeddedModel")
	public class PedidoEmbeddedModelOpenApi {
		
		private List<PedidoResumoModel> pedidos;

		public List<PedidoResumoModel> getPedidos() {
			return pedidos;
		}

		public void setPedidos(List<PedidoResumoModel> pedidos) {
			this.pedidos = pedidos;
		}
	}

	public PedidoEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(PedidoEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}

	public PageModelOpenApi getPage() {
		return page;
	}

	public void setPage(PageModelOpenApi page) {
		this.page = page;
	}
}