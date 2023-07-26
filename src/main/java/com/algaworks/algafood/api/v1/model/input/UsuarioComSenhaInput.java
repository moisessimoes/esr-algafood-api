package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioComSenhaInput extends UsuarioInput {
	
	@Schema(example = "sençalkapimi", required = true)
	@NotBlank
    private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}