package com.algaworks.algafood.api.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.ProdutoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("ProdutosModel")
public class ProdutosModelOpenApi { //19.46. Desafio: corrigindo a documentação dos endpoints de produtos
	
	private ProdutoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("ProdutosEmbeddedModel")
	public class ProdutoEmbeddedModelOpenApi {
		
		private List<ProdutoModel> produtos;

		public List<ProdutoModel> getProdutos() {
			return produtos;
		}

		public void setProdutos(List<ProdutoModel> produtos) {
			this.produtos = produtos;
		}
	}

	public ProdutoEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(ProdutoEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}