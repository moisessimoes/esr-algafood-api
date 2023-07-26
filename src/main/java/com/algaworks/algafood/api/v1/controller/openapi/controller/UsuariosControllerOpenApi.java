package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuariosControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Usuário cadastrado"),
//	})
	@Operation(summary = "Adiciona um novo usuário")
	public UsuarioModel salvar(@RequestBody(description = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuario);
	
	
	@Operation(summary = "Lista os usuário")
	public CollectionModel<UsuarioModel> listar();
	
	
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Busca um usuário por ID")
	public UsuarioModel buscar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Usuário atualizado"),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza um usuário por ID")
    public UsuarioModel atualizar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
    							  @RequestBody(description = "Representação de um usuário com novos dados", required = true) UsuarioInput usuarioInput);
    							  
    
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Senha alterada com sucesso"),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza a senha de um usuário por ID")
    public void alterarSenha(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
    						 @RequestBody(description = "Representação de uma nova senha", required = true) SenhaInput senha);
	
}