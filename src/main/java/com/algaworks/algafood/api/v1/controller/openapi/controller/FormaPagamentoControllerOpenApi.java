package com.algaworks.algafood.api.v1.controller.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

//@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {
	
//	@ApiOperation("Cadastra uma forma de pagamento")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada"),
//	})
	public FormaPagamento cadastrar(FormaPagamentoInput formaPagamentoInput);//@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamento);
	
	
//	@ApiOperation("Atualiza uma forma de pagamento")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
//		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
//	})
	public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput);//@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId, 
			//@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados", required = true) FormaPagamentoInput formaPagamento);
	
	
//	@ApiOperation("Busca uma forma de pagamento por ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
//	})
	public ResponseEntity<FormaPagamentoModel> buscar(Long formaPagamentoId,//@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId, 
																																		ServletWebRequest request);
	
	//@ApiOperation(value = "Lista as forma de pagamento", response = FormasPagamentoModelOpenApi.class)
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	//@ApiOperation("Exclui uma forma de pagamento")
	public void excluir(Long formaPagamentoId);//@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}