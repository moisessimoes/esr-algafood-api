package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	@ApiOperation("Lista os estados")
	public List<EstadoModel> listar();
	
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public EstadoModel buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);
	
	
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado"),
	})
	public Estado adicionar(@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) EstadoInput estado);
	
	
	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public Estado atualizar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId, 
							@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true) EstadoInput estado);
	
	
	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	public void delete(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

}