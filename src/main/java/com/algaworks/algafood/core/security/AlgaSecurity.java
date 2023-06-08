package com.algaworks.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.repositories.PedidoRepository;
import com.algaworks.algafood.repositories.RestauranteRepository;

@Component
public class AlgaSecurity { //23.17. Obtendo usuário autenticado no Resource Server
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Authentication getAuthentication() {
		
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {
		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usuario_id");
	}
	
	
	public boolean gerenciaRestaurante(Long restauranteId) {
		
		//23.28. Restringindo acessos de forma contextual (sensível à informação)
		
		return restauranteId != null ? restauranteRepository.existsResponsavel(restauranteId, getUsuarioId()) : false;
	}
	
	
	public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
		
		//23.31. Desafio: restringindo acessos aos endpoints de transição de status de pedidos
		
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
	}
	
	
	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		
		//23.38. Corrigindo lógica de restrição de acessos para Client Credentials Flow
		
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().compareTo(usuarioId) == 0;
	}
	
	//============================================================================================================
	
	public boolean hasAuthority(String authorityName) {
		
		//23.39. Gerando links do HAL dinamicamente de acordo com permissões do usuário
		
		return getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}
	
	
	public boolean podeGerenciarPedidos(String codigoPedido) {
		
		//23.39. Gerando links do HAL dinamicamente de acordo com permissões do usuário
		
		return hasAuthority("SCOPE_WRITE") && (hasAuthority("GERENCIAR_PEDIDOS") || gerenciaRestauranteDoPedido(codigoPedido));
	}
	
	//============================================================================================================
	//23.40. Desafio: gerando links do HAL dinamicamente de acordo com permissões
	
	public boolean isAuthenticated() {
		return getAuthentication().isAuthenticated();
	}
	
	public boolean hasWriteScope() {
	    return hasAuthority("SCOPE_WRITE");
	}

	public boolean hasReadScope() {
	    return hasAuthority("SCOPE_READ");
	}
	
	public boolean podeConsultar() {
		return hasReadScope() && isAuthenticated();
	}
	
	//===========================================================
	// COZINHAS
	public boolean podeEditarCozinhas() {
		return hasWriteScope() && hasAuthority("EDITAR_COZINHAS");
	}
	
	//===========================================================
	//RESTAURANTES
	public boolean podeGerenciarCadastroRestaurante() {
		return hasWriteScope() && hasAuthority("EDITAR_RESTAURANTES");
	}
	
	public boolean podeGerenciarFuncionamentoRestaurante(Long restauranteId) {
		return hasWriteScope() && (hasAuthority("EDITAR_RESTAURANTES") || gerenciaRestaurante(restauranteId));
	}
	
	//===========================================================
	//PEDIDOS
	public boolean podeCriarPedido() {
		return hasWriteScope() && isAuthenticated();
	}
	
	public boolean podeBuscarPedido(Long usuarioId, Long restauranteId) {
		return hasAuthority("CONSULTAR_PEDIDOS") || usuarioAutenticadoIgual(usuarioId) || gerenciaRestaurante(restauranteId);
	}
	
	public boolean podePesquisarPedido(Long usuarioId, Long restauranteId) {
		return hasReadScope() && (hasAuthority("CONSULTAR_PEDIDOS") || usuarioAutenticadoIgual(usuarioId) || gerenciaRestaurante(restauranteId));
	}
	
	//===========================================================
	//FORMA DE PAGAMENTO
	public boolean podeEditarFormaPagamento() {
		return hasWriteScope() && hasAuthority("EDITAR_FORMAS_PAGAMENTO");
	}
	
	//===========================================================
	//CIDADES
	public boolean podeEditarCidade() {
		return hasWriteScope() && hasAuthority("EDITAR_CIDADES");
	}
	
	//===========================================================
	//ESTADOS
	public boolean podeEditarEstado() {
		return hasWriteScope() && hasAuthority("EDITAR_ESTADOS");
	}
	
	//===========================================================
	//USUARIOS | GRUPOS | PERMISSOES
	public boolean podeEditarUsuariosGruposPermissoes() {
		return hasWriteScope() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
	}
	
	public boolean podeConsultarUsuariosGruposPermissoes() {
		return hasReadScope() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}
	
	public boolean podeAlterarUsuarios(Long usuarioId) {
		return hasWriteScope() && (usuarioAutenticadoIgual(usuarioId) || hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES"));
	}
	
	public boolean podeAlterarPropriaSenha(Long usuarioId) {
		return hasWriteScope() && usuarioAutenticadoIgual(usuarioId);
	}
	
	//===========================================================
	//ESTATISTICAS
	public boolean podenConsultarEstatisticas() {
		return hasReadScope() && hasAuthority("GERAR_RELATORIOS");
	}
}