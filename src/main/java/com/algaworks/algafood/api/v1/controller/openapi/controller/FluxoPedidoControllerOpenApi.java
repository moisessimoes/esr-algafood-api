package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Confirmação de pedido")
	public ResponseEntity<Void> confirmar(@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Registra entrega de pedido")
	public ResponseEntity<Void> entregar(@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Cancelamento de pedido")
	public ResponseEntity<Void> cancelar(@Parameter(description = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

}