package com.algaworks.algafood.api.v1.controller.openapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Cozinhas")
@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {
	
//	@ApiOperation("Cadastra uma cidade")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Cozinha cadastrada"),
//	})
	public Cozinha adicionar(CozinhaInput cozinhaInput);//@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInput cozinha);
	
	
//	@ApiOperation("Atualiza uma cozinha pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Cozinha atualizada"),
//		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
//	})
	public Cozinha atualizar(Long cozinhaId, CozinhaInput cozinhaInput);//@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId, 
			//@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true) CozinhaInput cozinha);
		
	
	
	//@ApiOperation("Lista as cozinhas com paginação")
	public PagedModel<CozinhaModel> listarComPaginacao(Pageable pageable);
		
	
	
//	@ApiOperation("Busca uma cozinha pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
//	})
	public CozinhaModel buscar(Long cozinhaId,//@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId, 
			HttpServletRequest req);
		
	
//	@ApiOperation("Exclui uma cidade pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Cozinha excluída"),
//		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
//	})
	public void delete(Long cozinhaId);//@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

}