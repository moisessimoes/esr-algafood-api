package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

//@Api(tags = "Estados")
public interface EstadoControllerOpenApi {
	
	//@ApiOperation("Lista os estados")
	public CollectionModel<EstadoModel> listar();
	
	
//	@ApiOperation("Busca um estado por ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	public EstadoModel buscar(Long estadoId);//@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Estado cadastrado"),
//	})
	public Estado adicionar(EstadoInput estadoInput);//@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true) EstadoInput estado);
	
	
//	@ApiOperation("Atualiza um estado por ID")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Estado atualizado"),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	public Estado atualizar(Long estadoId, EstadoInput estadoInput);//@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId, 
							//@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true) EstadoInput estado);
	
	
//	@ApiOperation("Exclui um estado por ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Estado excluído"),
//		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
//	})
	public void delete(Long estadoId);//@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

}