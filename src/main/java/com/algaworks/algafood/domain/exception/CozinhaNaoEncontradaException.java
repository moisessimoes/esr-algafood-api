package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String message) {
		super(message);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Cozinha de código %d não foi encontrado.", cozinhaId));
	}
}	