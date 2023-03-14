package com.algaworks.algafood.api.controller.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Responsáveis")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
	@ApiOperation("Lista os responsáveis do restaurante pelo ID do restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public List<UsuarioModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
	@ApiOperation("Desassocia um responsável do restaurante pelo ID do restaurante e usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
	})
	 public void desassociar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 				 @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
	 
	
	
	@ApiOperation("Associa um responsável do restaurante pelo ID do restaurante e usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", 
			response = Problem.class)
	})
	 public void associar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 			  @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

}