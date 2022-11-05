package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoService permissaoService;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	
	public Grupo buscarPorId(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
	
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscarPorId(grupoId);
	    Permissao permissao = permissaoService.buscarPorId(permissaoId);
	    
	    grupo.removerPermissao(permissao);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscarPorId(grupoId);
	    Permissao permissao = permissaoService.buscarPorId(permissaoId);
	    
	    grupo.adicionarPermissao(permissao);
	}     
	
	@Transactional
	public void excluir(Long grupoId) {
		
		try {
			grupoRepository.deleteById(grupoId);
			//Corrigindo bug de tratamento de exception de integridade de dados com flush do JPA
			grupoRepository.flush();
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(grupoId);
		}
	}
}
