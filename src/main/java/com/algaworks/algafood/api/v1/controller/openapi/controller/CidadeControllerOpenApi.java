package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Cidades")
@SecurityRequirement(name = "security_auth")
public interface CidadeControllerOpenApi {
	
	//@ApiOperation("Lista as cidades")
	public CollectionModel<CidadeModel> listar();
	
	
//	@ApiOperation("Busca uma cidade pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	public CidadeModel buscar(Long cidadeId);//@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);
	
	
	
//	@ApiOperation("Cadastra uma cidade")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Cidade cadastrada"),
//	})
	public CidadeModel adicionar(CidadeInput cidadeInput);//@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) CidadeInput cidade);
	
	
//	@ApiOperation("Atualiza uma cidade pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Cidade atualizada"),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	public Cidade atualizar(Long cidadeId, CidadeInput cidadeInput);//@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId, 
							//@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", required = true) CidadeInput cidade);
	
	
//	@ApiOperation("Exclui uma cidade pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Cidade excluída"),
//		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
//	})
	public void delete(Long cidadeId);//@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}