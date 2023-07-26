package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

public class ItemPedidoInput {
	
	@Schema(example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@Schema(example = "1", required = true)
	@NotNull
	@Positive
	@Min(value = 1)
	private Integer quantidade;
	
	@Schema(example = "Observação...")
	private String observacao;

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}