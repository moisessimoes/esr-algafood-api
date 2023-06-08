package com.algaworks.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity { //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	
	public @interface Cozinhas {
		
		/*23.26. Restringindo acesso a endpoints por escopos do OAuth2
		 * 
		 * Basicamente para restringir o acesso por escopo, basta adicionar a expressão regular do 
		 * Spring hasAuthority('SCOPE_NOME_DO_ESCOPO') como está sendo feito abaixo.
		 * */
		
		@PreAuthorize("@algaSecurity.podeEditarCozinhas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
	
	
	public @interface Restaurantes {
		
		//23.27. Desafio: restringindo acesso dos endpoints de restaurantes
		
		@PreAuthorize("@algaSecurity.podeGerenciarCadastroRestaurante()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCadastro { }
		
		@PreAuthorize("@algaSecurity.podeGerenciarFuncionamentoRestaurante(#restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento { /*23.28. Restringindo acessos de forma contextual (sensível à informação)*/ }
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
	
	
	public @interface Pedidos {
		
		//23.29. Restringindo acessos com @PostAuthorize
		
		//Abaixo temos a primeira forma de fazer, mas não era objetivo da aula, então vou deixar comentado
		
//		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or @algaSecurity.clienteDoPedido(#codigoPedido) or @algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeBuscar { }
		
		@PreAuthorize("@algaSecurity.podeCriarPedido()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }
		
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		//@PostAuthorize("@algaSecurity.podeBuscarPedido(returnObject.cliente.id, returnObject.restaurante.id)")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@algaSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or "
                + "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar { }
		
		
		@PreAuthorize("@algaSecurity.podePesquisarPedido(#filtro.clienteId, #filtro.restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar { /*23.30. Desafio: restringindo acessos ao endpoint de pesquisa de pedidos*/ }
		
		
		@PreAuthorize("@algaSecurity.podeGerenciarPedidos(#codigoPedido)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedidos { /*23.31. Desafio: restringindo acessos aos endpoints de transição de status de pedidos*/ }
	}
	
	
	public @interface FormaPagmento {
		
		//23.32. Desafio: restringindo acessos aos endpoints de formas de pagamentos
		
		@PreAuthorize("@algaSecurity.podeEditarFormaPagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
	
	
	public @interface Cidades {
		
		//23.33. Desafio: restringindo acessos aos endpoints de cidades e estados
		
		@PreAuthorize("@algaSecurity.podeEditarCidade()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
	
	
	public @interface Estados {
		
		//23.33. Desafio: restringindo acessos aos endpoints de cidades e estados
		
		@PreAuthorize("@algaSecurity.podeEditarEstado()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("@algaSecurity.podeConsultar()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
	
	
	public @interface UsuariosGruposPermissoes {
		
		//23.34. Desafio: restringindo acessos aos endpoints de usuários, grupos e permissões
		
		@PreAuthorize("@algaSecurity.podeEditarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("@algaSecurity.podeConsultarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("@algaSecurity.podeAlterarUsuarios(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario { }
		
		
		@PreAuthorize("@algaSecurity.podeAlterarPropriaSenha(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha { }
	}
	
	
	public @interface Estatisticas {
		
		//23.35. Desafio: restringindo acessos aos endpoints de estatísticas
		
		@PreAuthorize("@algaSecurity.podeConsultarEstatisticas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
	}
}