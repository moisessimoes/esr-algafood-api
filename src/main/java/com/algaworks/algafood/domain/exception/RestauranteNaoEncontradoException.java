package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Restaurante de código %d não foi encontrado.", restauranteId));
	}
}	