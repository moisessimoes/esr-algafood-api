package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuariosGrupoControllerOpenApi {
	
	@Operation(summary = "Lista os usuário por grupo pelo ID do usuário")
	public CollectionModel<GrupoModel> listar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
    
	
//	@ApiResponses({
//	    @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//	    @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Dessassociação do usuário de um grupo")
    public ResponseEntity<Void> desassociar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
    										@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);
    
	
//	@ApiResponses({
//	    @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//	    @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Associação do usuário com um grupo")
    public ResponseEntity<Void> associar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
    									 @Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);
	
}