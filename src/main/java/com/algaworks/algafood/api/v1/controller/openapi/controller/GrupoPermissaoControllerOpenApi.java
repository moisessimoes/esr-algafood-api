package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões")
public interface GrupoPermissaoControllerOpenApi {
	
//	@ApiResponses({
//	    @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
//	    @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Lista as permissões associadas a um grupo")
	CollectionModel<PermissaoModel> listar(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
//	})
	@Operation(summary = "Dessassociação de permissão com o grupo")
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId, 
									 @Parameter(description = "ID da permissão", example = "1", required = true) Long permissaoId);
									 

	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
//	})
	@Operation(summary = "Associação de permissão com o grupo")
	ResponseEntity<Void> associar(@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId, 
								  @Parameter(description = "ID da permissão", example = "1", required = true) Long permissaoId);
								  

}