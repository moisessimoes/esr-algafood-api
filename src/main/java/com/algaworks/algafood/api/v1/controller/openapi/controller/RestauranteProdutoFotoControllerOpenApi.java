package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante - Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {
	
	@Operation(summary = "Atualiza foto do produto de um restaurante")
	public FotoProdutoModel atualizarFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
										  @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId, 
										  @RequestBody(required = true) FotoProdutoInput fotoProdutoInput) throws IOException; 
	
	
	
	@Operation(summary = "Busca uma foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FotoProdutoModel.class)),
					@Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = MediaType.IMAGE_PNG_VALUE, schema = @Schema(type = "string", format = "binary"))
			})
	})
	public FotoProdutoModel buscarFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
									   @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);
	
	
	
	@Operation(summary = "Recupera ou salva uma nova foto de um produto de um restaurante", hidden = true)
	public ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	
	
	@Operation(summary = "Exclui a foto de um produto de um restaurante")
	public void excluirFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
							@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

}