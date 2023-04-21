package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("GruposModel")
public class GruposModelOpenApi { //19.44. Desafio: corrigindo a documentação dos endpoints de grupos
	
	private GrupoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GruposEmbeddedModel")
	public class GrupoEmbeddedModelOpenApi {
		
		private List<GrupoModel> grupos;

		public List<GrupoModel> getGrupos() {
			return grupos;
		}

		public void setGrupos(List<GrupoModel> grupos) {
			this.grupos = grupos;
		}
	}
	
	public GrupoEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(GrupoEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}