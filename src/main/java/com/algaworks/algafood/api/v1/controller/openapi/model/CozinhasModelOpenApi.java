package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.CozinhaModel;

//@ApiModel("CozinhasModel")
public class CozinhasModelOpenApi  {
	
	//Isso era referente a primeira correção feita, mas não vale mais. extends PagedModelOpenApi<CozinhaModel>
	
	 /*19.41. Corrigindo a paginação na documentação
	 * 
	 * Realizando uma segunda correção na documentação devido ao uso do HATEOAS*/
	
	private CozinhasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	//@ApiModel("CidadesEmbeddedModel")
	public class CozinhasEmbeddedModelOpenApi {
		
		private List<CozinhaModel> cozinhas;

		public List<CozinhaModel> getCozinhas() {
			return cozinhas;
		}

		public void setCozinhas(List<CozinhaModel> cozinhas) {
			this.cozinhas = cozinhas;
		}
	}

	public CozinhasEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(CozinhasEmbeddedModelOpenApi _embedded) {
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