package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;

public class RestauranteInput {
	
	@Schema(example = "Sal e Pimenta", required = true)
	@NotBlank
	private String nome;
	
	@Schema(example = "2.50", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}
	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
	public CozinhaIdInput getCozinha() {
		return cozinha;
	}
	public void setCozinha(CozinhaIdInput cozinha) {
		this.cozinha = cozinha;
	}
	public EnderecoInput getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoInput endereco) {
		this.endereco = endereco;
	}
}