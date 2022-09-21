package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message) {
		super(message);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado de código %d não foi encontrado.", estadoId));
	}
}	