package com.algaworks.algafood.api.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GruposControllerOpenApi {
	
	@ApiOperation("Lista os grupos")
	public CollectionModel<GrupoModel> listar();
	
	@ApiOperation("Busca um grupo pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	public Grupo adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true) GrupoInput grupo);
	
	
	@ApiOperation("Atualiza um grupo pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public Grupo atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId, 
						   @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true) GrupoInput grupo);
	
	
	@ApiOperation("Exclui uma cidade pelo ID")
	public void delete(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
}