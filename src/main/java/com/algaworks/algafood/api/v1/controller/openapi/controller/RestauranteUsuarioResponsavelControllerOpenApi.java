package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Restaurante - Responsáveis")
@SecurityRequirement(name = "security_auth")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
//	@ApiOperation("Lista os responsáveis do restaurante pelo ID do restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public CollectionModel<UsuarioModel> listar(Long restauranteId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Desassocia um responsável do restaurante pelo ID do restaurante e usuário")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
//	})
	 public ResponseEntity<Void> desassociar(Long restaurnateId, Long usuarioId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 				 //@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
	 
	
	
//	@ApiOperation("Associa um responsável do restaurante pelo ID do restaurante e usuário")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", 
//			response = Problem.class)
//	})
	 public ResponseEntity<Void> associar(Long restaurnateId, Long usuarioId);//@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			 			  //@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

}