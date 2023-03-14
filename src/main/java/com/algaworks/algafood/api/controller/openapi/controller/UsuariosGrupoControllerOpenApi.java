package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Grupo de Usuários")
public interface UsuariosGrupoControllerOpenApi {
	
	@ApiOperation("Lista os usuários pelo grupo")
	public List<GrupoModel> listar(Long usuarioId);
    
	@ApiOperation("Desassocia um usuário de um grupo")
    public void desassociar(Long usuarioId, Long grupoId);
    
	@ApiOperation("Associa um usuário a um grupo")
    public void associar(Long usuarioId, Long grupoId);
	
}