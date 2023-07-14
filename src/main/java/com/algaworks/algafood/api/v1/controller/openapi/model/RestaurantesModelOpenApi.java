package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;

//@ApiModel("RestaurantesModel")
public class RestaurantesModelOpenApi { //19.47. Desafio: corrigindo a documentação dos endpoints de restaurantes e usuários
	
	private RestauranteEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	//@ApiModel("RestaurantesEmbeddedModel")
	public class RestauranteEmbeddedModelOpenApi {
		
		private List<RestauranteBasicoModel> restaurantes;

		public List<RestauranteBasicoModel> getRestaurantes() {
			return restaurantes;
		}

		public void setRestaurantes(List<RestauranteBasicoModel> restaurantes) {
			this.restaurantes = restaurantes;
		}
	}

	public RestauranteEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(RestauranteEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}