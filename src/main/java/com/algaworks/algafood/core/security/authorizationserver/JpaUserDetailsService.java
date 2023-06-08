package com.algaworks.algafood.core.security.authorizationserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.repositories.UsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService { //23.14. Autenticando usuário com dados do banco de dados
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
		
		return new AuthUser(usuario, getAuthorities(usuario));
	}
	
	
	private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
		
		//23.19. Carregando as permissões concedidas na autenticação
		
		return usuario.getGrupos().stream().flatMap(grupo -> grupo.getPermissoes().stream())
										   .map(permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase())).collect(Collectors.toSet());
	}
}