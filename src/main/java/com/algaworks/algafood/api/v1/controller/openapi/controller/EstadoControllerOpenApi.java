package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {
	
	@Operation(summary = "Lista os estados")
	public CollectionModel<EstadoModel> listar();
	
	
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Busca um estado por ID")
	public EstadoModel buscar(@Parameter(description = "ID da estado", example = "1", required = true) Long estadoId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Estado cadastrado"),
//	})
	@Operation(summary = "Adiciona um novo estado")
	public Estado adicionar(@RequestBody(description = "Representação de uma novo estado", required = true) EstadoInput estadoInput);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Estado atualizado"),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza um estado por ID")
	public Estado atualizar(Long estadoId, EstadoInput estadoInput);//@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId, 
							//@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true) EstadoInput estado);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Estado excluído"),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Exclui um estado por ID")
	public void delete(Long estadoId);//@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

}