package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.repositories.RestauranteRepository;

@Service
public class RestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso.";

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	public Restaurante buscarPorId(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinhaAssociada = cozinhaService.buscarPorId(cozinhaId);
		
		Cidade cidade = cidadeService.buscarPorId(cidadeId);
		
		restaurante.setCozinha(cozinhaAssociada);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurante);
	}
	
	
	@Transactional
	public void ativar(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.inativar();
	}
	
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}
	
	
	@Transactional
	public void abrir(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.fechar();
	}
	
	
	@Transactional
	public void adicionarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(formaPagamentoId);
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	
	@Transactional
	public void removerFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(formaPagamentoId);
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	
	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarPorId(restauranteId);
	    Usuario usuario = usuarioService.buscarPorId(usuarioId);
	    
	    restaurante.removerResponsavel(usuario);
	}

	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarPorId(restauranteId);
	    Usuario usuario = usuarioService.buscarPorId(usuarioId);
	    
	    restaurante.adicionarResponsavel(usuario);
	}
	
	
	@Transactional
	public void excluir(Long restauranteId) {
		
		try {
			restauranteRepository.deleteById(restauranteId);
			//Corrigindo bug de tratamento de exception de integridade de dados com flush do JPA
			restauranteRepository.flush();
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
			
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		}
	}
}