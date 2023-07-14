package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

public class CozinhaInput {
	
	//@ApiModelProperty(position = 0, example = "Italiana", required = true)
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}