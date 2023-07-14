package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.http.ResponseEntity;

//@Api(tags = "Fluxo Pedido")
public interface FluxoPedidoControllerOpenApi {
	
//	@ApiOperation("Confirmação de pedido")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> confirmar(String codigoPedido);//@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);
	
	
//	@ApiOperation("Registrar entrega de pedido")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> entregar(String codigoPedido);//@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);
	
	
//	@ApiOperation("Cancelamento de pedido")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> cancelar(String codigoPedido);//@ApiParam(value = "Código do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

}
