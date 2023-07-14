package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Pedidos")
@SecurityRequirement(name = "security_auth")
public interface PedidoControllerOpenApi {
	
//	@ApiOperation("Registra um pedido")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Pedido registrado"),
//	})
	public PedidoModel adicionar(PedidoInput pedidoInput);//@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);
	    	

//	@ApiOperation("Pesquisa os pedidos")
//	@ApiImplicitParams({
//		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
//				name = "campos", paramType = "query", type = "string")
//	})
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable);
	    	
	
//	@ApiOperation("Busca um pedido por código")
//	@ApiResponses({
//		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
//	})
//	@ApiImplicitParams({
//		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
//				name = "campos", paramType = "query", type = "string")
//	})
	public PedidoModel buscar(String codigoPedido);//@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

}