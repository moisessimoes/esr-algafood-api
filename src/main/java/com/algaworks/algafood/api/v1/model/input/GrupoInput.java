package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

public class GrupoInput {

	//@ApiModelProperty(example = "Gerente")
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}