package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante - Produtos")
public interface RestauranteProdutoControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Produto cadastrado"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Adiciona um novo produto a um restaurante")
	public ProdutoModel adicionar(@RequestBody(description = "Representação de um novo produto", required = true) ProdutoInput produtoInput, 
								@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
								 
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Produto atualizado"),
//		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza um produto de um restaurante")
	public ProdutoModel atualizar(@RequestBody(description = "Representação de um produto com dados", required = true) ProdutoInput produtoInput, 
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId); 
								   
								  
	
	
	
	@Operation(summary = "Lista os produtos de um restaurante por ID")
	public CollectionModel<ProdutoModel> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId, 
												@Parameter(description = "Indica se deve ou não incluir produtos inativos no resultado", example = "false", required = true) Boolean incluirInativos);
			
	
	
	
	@Operation(summary = "Busca um produto de um restaurante por ID")
	public ProdutoModel buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
							   @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);
	
}