package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Pedido;

public class PedidoCanceladoEvent {
	
	//15.11. Publicando Domain Events a partir do Aggregate Root
	
	private Pedido pedido;

	public PedidoCanceladoEvent(Pedido pedido) {
		super();
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}