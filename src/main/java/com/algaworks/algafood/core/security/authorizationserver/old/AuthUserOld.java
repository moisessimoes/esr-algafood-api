package com.algaworks.algafood.core.security.authorizationserver.old;

//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import com.algaworks.algafood.domain.model.Usuario;

//public class AuthUserOld extends User { //23.14. Autenticando usuário com dados do banco de dados
//	
//	private static final long serialVersionUID = 1L;
//	
//	private Long userId;
//	private String fullName;
//	
//	public AuthUserOld(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
//		
//		super(usuario.getEmail(), usuario.getSenha(), authorities);
//		
//		this.userId = usuario.getId();
//		this.fullName = usuario.getNome();
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//}