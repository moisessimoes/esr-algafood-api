package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		/*Metodo detach implementado no CustomJpaRepository
		 * 
		 * Serve para informar ao JPA que ele não deve gerenciar a entidade em questão
		 * */
		
		usuarioRepository.detach(usuario);
		
		//Antes de atualizar o usuário é feita essa validação para não permitir dois usuários com o mesmo email
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		return usuarioRepository.save(usuario);
	}
	
	
	@Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		
        Usuario usuario = buscarPorId(usuarioId);
        
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
        usuario.setSenha(novaSenha);
    }
	
	
	public Usuario buscarPorId(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	
	@Transactional
	public void associarAoGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarPorId(usuarioId);
		Grupo grupo = grupoService.buscarPorId(grupoId);
		usuario.adicionarGrupoAoUsuario(grupo);
	}
	
	@Transactional
	public void desassociarDoGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarPorId(usuarioId);
		Grupo grupo = grupoService.buscarPorId(grupoId);
		usuario.removerGrupoDoUsuario(grupo);
	}
	
	
	@Transactional
	public void excluir(Long usuarioId) {
		
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(usuarioId);
		}
	}
}