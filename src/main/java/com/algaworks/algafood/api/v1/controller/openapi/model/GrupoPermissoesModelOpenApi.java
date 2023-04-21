package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("GrupoPermissoesModel")
public class GrupoPermissoesModelOpenApi { //19.44. Desafio: corrigindo a documentação dos endpoints de grupos
	
	private GrupoPermissoesEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GruposPermissoesEmbeddedModel")
	public class GrupoPermissoesEmbeddedModelOpenApi {
		
		private List<PermissaoModel> permissoes;

		public List<PermissaoModel> getPermissoes() {
			return permissoes;
		}

		public void setPermissoes(List<PermissaoModel> permissoes) {
			this.permissoes = permissoes;
		}
	}
	
	public GrupoPermissoesEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(GrupoPermissoesEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}