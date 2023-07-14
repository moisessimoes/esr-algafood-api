package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Usuários")
@SecurityRequirement(name = "security_auth")
public interface UsuariosControllerOpenApi {
	
//	@ApiOperation("Cadastra um usuário")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Usuário cadastrado"),
//	})
	public UsuarioModel salvar(UsuarioComSenhaInput usuario);//@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuario);
	
	
	//@ApiOperation("Lista os usuários")
	public CollectionModel<UsuarioModel> listar();
	
	
//	@ApiOperation("Busca um usuário por ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
	public UsuarioModel buscar(Long usuarioId);//@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
	
	
//	@ApiOperation("Atualiza um usuário por ID")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Usuário atualizado"),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
    public UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);//@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
    							  //@ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true) UsuarioInput usuarioInput);
    
	
//	@ApiOperation("Atualiza a senha de um usuário")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Senha alterada com sucesso"),
//		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
//	})
    public void alterarSenha(Long usuarioId, SenhaInput senha);//@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
    						 //@ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true) SenhaInput senha);
	
}