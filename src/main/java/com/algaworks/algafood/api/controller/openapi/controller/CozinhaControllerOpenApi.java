package com.algaworks.algafood.api.controller.openapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada"),
	})
	public Cozinha adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInput cozinha);
	
	
	@ApiOperation("Atualiza uma cozinha pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public Cozinha atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId, 
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true) CozinhaInput cozinha);
		
	
	
	@ApiOperation("Lista as cozinhas com paginação")
	public PagedModel<CozinhaModel> listarComPaginacao(Pageable pageable);
		
	
	
	@ApiOperation("Busca uma cozinha pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId, HttpServletRequest req);
		
	
	@ApiOperation("Exclui uma cidade pelo ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public void delete(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

}