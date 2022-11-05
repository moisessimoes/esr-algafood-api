package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradoException(String message) {
		super(message);
	}
	
	public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
		this(String.format("Forma de pagamento de código %d não foi encontrado.", formaPagamentoId));
	}
}