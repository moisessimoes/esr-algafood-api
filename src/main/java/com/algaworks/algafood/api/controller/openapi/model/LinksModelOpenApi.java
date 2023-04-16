package com.algaworks.algafood.api.controller.openapi.model;

import io.swagger.annotations.ApiModel;

@ApiModel("Links")
public class LinksModelOpenApi { //19.39. Corrigindo as propriedades de links na documentação
	
	private LinkModel rel;
	
	@ApiModel("Link")
	public class LinkModel {
		
		private String href;
		private boolean templated;
		
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public boolean isTemplated() {
			return templated;
		}
		public void setTemplated(boolean templated) {
			this.templated = templated;
		}
	}

	public LinkModel getRel() {
		return rel;
	}

	public void setRel(LinkModel rel) {
		this.rel = rel;
	}
}