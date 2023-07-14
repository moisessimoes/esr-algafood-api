package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

public class FormaPagamentoInput {

	//@ApiModelProperty(example = "Bitcoin")
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}