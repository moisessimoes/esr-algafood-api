package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Grupo de Permissões")
@SecurityRequirement(name = "security_auth")
public interface GrupoPermissaoControllerOpenApi {
	
//	@ApiOperation("Lista as permissões associadas a um grupo")
//	@ApiResponses({
//	    @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
//	    @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
//	})
	CollectionModel<PermissaoModel> listar(Long grupoId);//@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);
	
	
//	@ApiOperation("Desassociação de permissão com grupo")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
//	})
	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);//@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
									 //@ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

	
//	@ApiOperation("Associação de permissão com grupo")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
//	})
	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);//@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
								  //@ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

}