package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante - Formas de Pagamento")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Lista as formas de pagamento de um restaurante por ID")
	public CollectionModel<FormaPagamentoModel> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Associa uma forma de pagamento a um restaurante por ID do restaurante e da forma de pagamento")
	public ResponseEntity<Void> associarFormaPagamento(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
													   @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formnaPagamentoId);
									  
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Dessassocia uma forma de pagamento a um restaurante por ID do restaurante e da forma de pagamento")
	public ResponseEntity<Void> desassociarFormaPagamento(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
														  @Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId); 
										  
	
}