package com.algaworks.algafood.api.v2.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v2.model.CidadeModelV2;

import io.swagger.annotations.ApiModel;

@ApiModel("CidadesModel")
public class CidadesModelOpenApiV2 { //19.40. Corrigindo a documentação dos endpoints de cidades
	
	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("CidadesEmbeddedModel")
	public class CidadeEmbeddedModelOpenApi {
		
		private List<CidadeModelV2> cidades;

		public List<CidadeModelV2> getCidades() {
			return cidades;
		}

		public void setCidades(List<CidadeModelV2> cidades) {
			this.cidades = cidades;
		}
	}

	public CidadeEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(CidadeEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}