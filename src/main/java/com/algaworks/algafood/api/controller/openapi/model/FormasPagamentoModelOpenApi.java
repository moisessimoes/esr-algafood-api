package com.algaworks.algafood.api.controller.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;

@ApiModel("FormasPagamentoModel")
public class FormasPagamentoModelOpenApi { //19.43. Desafio: corrigindo a documentação dos endpoints de formas de pagamento
	
	private FormaPagamentoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("FormasPagamentosEmbeddedModel")
	public class FormaPagamentoEmbeddedModelOpenApi {
		
		private List<FormaPagamentoModel> formasPagamento;

		public List<FormaPagamentoModel> getFormasPagamento() {
			return formasPagamento;
		}

		public void setFormasPagamento(List<FormaPagamentoModel> formasPagamento) {
			this.formasPagamento = formasPagamento;
		}
	}
	
	public FormaPagamentoEmbeddedModelOpenApi get_embedded() {
		return _embedded;
	}

	public void set_embedded(FormaPagamentoEmbeddedModelOpenApi _embedded) {
		this._embedded = _embedded;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}
}