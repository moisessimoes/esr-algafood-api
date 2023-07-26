package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoInput {
	
	@Schema(example = "12345678", required = true)
	@NotBlank
	private String cep;
	
	@Schema(example = "Rua ABC", required = true)
	@NotBlank
	private String logradouro;
	
	@Schema(example = "123", required = true)
	@NotBlank
	private String numero;
	
	@Schema(example = "Ao lado da padaria ABC")
	private String complemento;
	
	@Schema(example = "Bairro ABC", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public CidadeIdInput getCidade() {
		return cidade;
	}

	public void setCidade(CidadeIdInput cidade) {
		this.cidade = cidade;
	}
}