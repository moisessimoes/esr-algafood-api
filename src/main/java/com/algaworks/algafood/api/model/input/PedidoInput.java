package com.algaworks.algafood.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class PedidoInput {
	
	@ApiModelProperty(position = 0)
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@ApiModelProperty(position = 1)
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@ApiModelProperty(position = 2)
	@Valid
	@NotNull
	private EnderecoInput enderecoEntrega;
	
	@ApiModelProperty(position = 3)
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itens;

	public RestauranteIdInput getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(RestauranteIdInput restaurante) {
		this.restaurante = restaurante;
	}

	public FormaPagamentoIdInput getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoIdInput formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public EnderecoInput getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoInput enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<ItemPedidoInput> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoInput> itens) {
		this.itens = itens;
	}
}