package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioInput {
	
	@Schema(example = "Serkan", required = true)
	@NotBlank
	private String nome;
	
	@Schema(example = "artlife@mail.com", required = true)
	@NotBlank
	@Email
	private String email;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}