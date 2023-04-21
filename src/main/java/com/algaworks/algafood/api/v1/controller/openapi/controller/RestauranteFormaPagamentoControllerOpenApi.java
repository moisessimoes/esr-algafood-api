package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurante - Formas de Pagamento")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as forma de pagamento de um restaurante pelo ID do restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public CollectionModel<FormaPagamentoModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
	@ApiOperation("Associa uma forma de pagamento a um restaurante pelo ID do restaurante e da forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void> associarFormaPagamento(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
									   @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
	
	
	@ApiOperation("Desassocia uma forma de pagamento a um restaurante pelo ID do restaurante e da forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
	})
	public ResponseEntity<Void> desassociarFormaPagamento(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
										  @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
}