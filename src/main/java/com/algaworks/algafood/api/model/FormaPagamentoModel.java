package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;

public class FormaPagamentoModel {
	
	@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	@ApiModelProperty(position = 1, example = "Dinheiro")
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}