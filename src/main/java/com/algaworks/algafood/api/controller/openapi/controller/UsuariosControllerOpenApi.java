package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuários")
public interface UsuariosControllerOpenApi {
	
	@ApiOperation("Cadastra um usuário")
	public UsuarioModel salvar(UsuarioComSenhaInput usuario);
	
	@ApiOperation("Lista os usuários")
	public List<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário pelo ID")
	public UsuarioModel buscar(Long usuarioId);
	
	@ApiOperation("Atualiza um usuário pelo ID")
    public UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);
    
	@ApiOperation("Atualiza a senha de um usuário pelo ID")
    public void alterarSenha(Long usuarioId, SenhaInput senha);
	
}