package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.repositories.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	private static final String MSG_FORMA_PGTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso.";
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	
	public FormaPagamento buscarPorId(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(() -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
	}
	
	@Transactional
	public void excluir(Long formaPagamentoId) {
		
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
			//Corrigindo bug de tratamento de exception de integridade de dados com flush do JPA
			formaPagamentoRepository.flush();
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PGTO_EM_USO, formaPagamentoId));
		
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradoException(formaPagamentoId);
		}
	}
}