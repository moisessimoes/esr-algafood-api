package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemPedidoInput {
	
	//@ApiModelProperty(position = 0, example = "1")
	@NotNull
	private Long produtoId;
	
	//@ApiModelProperty(position = 1, example = "1")
	@NotNull
	@Positive
	@Min(value = 1)
	private Integer quantidade;
	
	//@ApiModelProperty(position = 2, example = "Uma observação")
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