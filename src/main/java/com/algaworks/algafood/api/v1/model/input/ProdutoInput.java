package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoInput {
	
	@Schema(example = "Hot Dog", required = true)
	@NotBlank
	private String nome;
	
	@Schema(example = "Hot Dog completo", required = true)
	@NotBlank
	private String descricao;
	
	@Schema(example = "10.00", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@Schema(example = "true", required = true)
	@NotNull
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}