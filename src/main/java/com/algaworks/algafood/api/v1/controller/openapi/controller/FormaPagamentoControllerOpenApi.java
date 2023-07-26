package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {
	
	@Operation(summary = "Adiciona uma nova forma de pagamento")
	public FormaPagamento cadastrar(@RequestBody(description = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);
	
	
	@Operation(summary = "Atualiza uma forma de pagamento por ID")
	public FormaPagamento atualizar(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId, 
									@RequestBody(description = "Representação de uma forma de pagamento com novos dados", required = true)FormaPagamentoInput formaPagamentoInput); 
	
	
	@Operation(summary = "Busca uma forma de pagamento por ID")
	public ResponseEntity<FormaPagamentoModel> buscar(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId, ServletWebRequest request);
	
	
	@Operation(summary = "Lista as formas de pagamento")
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	
	@Operation(summary = "Exclui uma forma de pagamento por ID")
	public void excluir(@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}