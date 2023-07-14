package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

//@ApiModel("UsuariosModel")
public class UsuariosModelOpenApi { //19.47. Desafio: corrigindo a documentação dos endpoints de restaurantes e usuários
	
	private UsuarioEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	//@ApiModel("UsuariosEmbeddedModel")
	public class UsuarioEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;

		public List<UsuarioModel> getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(List<UsuarioModel> usuarios) {
			this.usuarios = usuarios;
		}
	}

	public UsuarioEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(UsuarioEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}