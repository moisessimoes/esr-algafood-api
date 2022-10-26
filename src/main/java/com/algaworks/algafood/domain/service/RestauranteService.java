package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repositories.RestauranteRepository;

@Service
public class RestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso.";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	
	public Restaurante buscarPorId(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinhaAssociada = cozinhaService.buscarPorId(cozinhaId);
		
		restaurante.setCozinha(cozinhaAssociada);
		return restauranteRepository.save(restaurante);
	}
	
	
	@Transactional
	public void ativar(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		//restaurante.setAtivo(true);
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		
		Restaurante restaurante = buscarPorId(restauranteId);
		//restaurante.setAtivo(false);
		restaurante.inativar();
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
