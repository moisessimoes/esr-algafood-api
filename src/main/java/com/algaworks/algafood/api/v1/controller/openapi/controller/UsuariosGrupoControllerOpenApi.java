package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Grupo de Usuários")
@SecurityRequirement(name = "security_auth")
public interface UsuariosGrupoControllerOpenApi {
	
	//@ApiOperation("Lista os usuários pelo grupo")
	public CollectionModel<GrupoModel> listar(Long usuarioId);
    
	
//	@ApiOperation("Desassociação de grupo com usuário")
//	@ApiResponses({
//	    @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//	    @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
//	})
    public ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);//@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
    										//@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);
    
	
//	@ApiOperation("Associação de grupo com usuário")
//	@ApiResponses({
//	    @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//	    @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
//	})
    public ResponseEntity<Void> associar(Long usuarioId, Long grupoId);//@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
    									 //@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);
	
}