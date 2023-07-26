package com.algaworks.algafood.api.v1.controller.openapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import com.algaworks.algafood.domain.model.Cozinha;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@Operation(summary = "Adiciona uma nova cozinha")
	public Cozinha adicionar(@RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);
	
	
	@Operation(summary = "Atualiza uma cozinha por ID")
	public Cozinha atualizar(@Parameter(description = "ID da cozinha", example = "1", required = true) Long cozinhaId, 
							@RequestBody(description = "Representação de uma cozinha", required = true) CozinhaInput cozinhaInput); 
			
		
	
	@PageableParameter
	public PagedModel<CozinhaModel> listarComPaginacao(@Parameter(hidden = true) Pageable pageable);
		
	
	
	@Operation(summary = "Busca uma cozinha por ID")
	public CozinhaModel buscar(@Parameter(description = "ID da cozinha", example = "1", required = true) Long cozinhaId, HttpServletRequest req);
		
	
	@Operation(summary = "Exclui uma cozinha por ID")
	public void delete(@Parameter(description = "ID da cozinha", example = "1", required = true) Long cozinhaId);

}