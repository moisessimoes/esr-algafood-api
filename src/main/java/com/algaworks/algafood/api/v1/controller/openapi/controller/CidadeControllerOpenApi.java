package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
	
	@Operation(summary = "Lista as cidades")
	public CollectionModel<CidadeModel> listar();
	
	
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	@Operation(summary = "Busca a cidade por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(ref = "Problema")))
	})
	public CidadeModel buscar(@Parameter(description = "ID da cidade", example = "1", required = true) Long cidadeId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Cidade cadastrada"),
//	})
	@Operation(summary = "Adiciona uma nova cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido.")
	public CidadeModel adicionar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Cidade atualizada"),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	@Operation(summary = "Atualiza uma cidade por ID")
	public Cidade atualizar(@Parameter(description = "ID da cidade", example = "1", required = true) Long cidadeId, 
							@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Cidade excluída"),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	@Operation(summary = "Exclui uma cidade por ID")
	public void delete(@Parameter(description = "ID da cidade", example = "1", required = true) Long cidadeId);

}