package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GruposControllerOpenApi {
	
	@Operation(summary = "Lista os grupos")
	public CollectionModel<GrupoModel> listar();
	
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Busca um grupo por ID")
	public GrupoModel buscar(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Grupo cadastrado"),
//	})
	@Operation(summary = "Adiciona um novo grupo")
	public Grupo adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Grupo atualizado"),
//		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza um grupo por ID")
	public Grupo atualizar(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId, 
						   @RequestBody(description = "Representação de um grupo com dados", required = true) GrupoInput grupoInput); 
						   
	
	
	@Operation(summary = "Exclui um grupo por ID")
	public void delete(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);
}