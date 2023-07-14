package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Restaurante - Produtos - Fotos")
@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoFotoControllerOpenApi {
	
//	@ApiOperation("Atualiza foto do produto de um restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Foto do produto atualizada"),
//		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
//	})
	public FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput, MultipartFile arquivo) throws IOException ;//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
										  //@ApiParam(value = "ID do produto", example = "1", required = true)     Long produtoId, 
										  																		 //FotoProdutoInput fotoProdutoInput,
										  //@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)	MultipartFile arquivo) throws IOException;
	
	
	
//	@ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
//	})
	public FotoProdutoModel buscarFoto(Long restauranteId, Long produtoId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
									   //@ApiParam(value = "ID do produto", example = "1", required = true)     Long produtoId);
	
	
	
	//@ApiOperation(value = "Recupera ou salva uma nova foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	
	
//	@ApiOperation("Exclui a foto do produto de um restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Foto do produto excluída"),
//		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
//	})
	public void excluirFoto(Long restauranteId, Long produtoId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			  				//@ApiParam(value = "ID do produto", example = "1", required = true)     Long produtoId);

}