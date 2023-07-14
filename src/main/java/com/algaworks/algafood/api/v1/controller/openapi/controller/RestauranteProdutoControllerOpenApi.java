package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Restaurante - Produtos")
@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoControllerOpenApi {
	
//	@ApiOperation("Adiciona um novo produto a um restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Produto cadastrado"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public ProdutoModel adicionar(ProdutoInput produtoInput, Long restauranteId);//@ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput, 
								  //@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Atualiza um produto de um restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Produto atualizado"),
//		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
//	})
	public ProdutoModel atualizar(ProdutoInput produtoInput, Long restauranteId, Long produtoId);//@ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput, 
								  //@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
								  //@ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);
	
	
	
	//@ApiOperation("Lista os produtos de um restaurante pelo ID do restaurante")
	public CollectionModel<ProdutoModel> listar(Long restaurnateId, Boolean incluirInativos);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			//@ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", example = "false", defaultValue = "false") Boolean incluirInativos);
	
	
	
	//@ApiOperation("Busca um produto de um restaurante pelo ID do restaurante e do produto")
	public ProdutoModel buscar(Long restauranteId, Long produtoId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
							   //@ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);
	
}