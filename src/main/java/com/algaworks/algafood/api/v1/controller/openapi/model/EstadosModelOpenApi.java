package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.EstadoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("EstadosModel")
public class EstadosModelOpenApi { //19.42. Desafio: corrigindo a documentação dos endpoints de estados
	
	private EstadoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("EstadosEmbeddedModel")
	public class EstadoEmbeddedModelOpenApi {
		
		private List<EstadoModel> estados;

		public List<EstadoModel> getEstados() {
			return estados;
		}

		public void setEstados(List<EstadoModel> estados) {
			this.estados = estados;
		}
	}

	public EstadoEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(EstadoEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}