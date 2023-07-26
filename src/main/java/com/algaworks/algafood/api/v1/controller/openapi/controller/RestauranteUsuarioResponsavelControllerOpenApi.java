package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurante - Responsáveis")
public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Lista os responsáveis do restaurante por ID")
	public CollectionModel<UsuarioModel> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
//	})
	 @Operation(summary = "Dessassocia um responsável do restaurante por ID do restaurante e usuário")
	 public ResponseEntity<Void> desassociar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId, 
			 								 @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
	 
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", 
//			response = Problem.class)
//	})
	 @Operation(summary = "Associa um responsável do restaurante por ID do restaurante e usuário")
	 public ResponseEntity<Void> associar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId, 
			 							  @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

}