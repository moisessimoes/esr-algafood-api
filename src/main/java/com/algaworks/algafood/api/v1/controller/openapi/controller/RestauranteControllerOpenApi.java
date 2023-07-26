package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Restaurante;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {
	
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Restaurante cadastrado"),
//	})
	@Operation(summary = "Adiciona um novo restaurante")
	public RestauranteModel adicionar(@RequestBody(description = "Representação de um novo restaurante", required = true) RestauranteInput restauranteInput);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Restaurante atualizado"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Atualiza um restaurante por ID")
	public RestauranteModel atualizar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
									  @RequestBody(description = "Representação de um restaurante com dados", required = true) RestauranteInput restauranteInput);
			
	
	
	
	@Operation(summary = "Atualiza parcialmente um restaurante por ID")
	public Restaurante atualizarParcial(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId, Map<String, Object> campos, HttpServletRequest req);
	
//	@ApiImplicitParams({ //18.28. Descrevendo parâmetros de projeções em endpoints de consultas
//    	@ApiImplicitParam(name = "projecao", value = "Nome da projeção de pedidos.", paramType = "query", type = "string", allowableValues = "apenas-nome")
//    })
	//@JsonView(RestauranteView.Resumo.class)
	
	
	@Operation(summary = "Lista os restaurantes", parameters = {
			@Parameter(name = "projecao", description = "Nome da projeção", example = "apenas-nome", in = ParameterIn.QUERY, required = false)
	})
	public CollectionModel<RestauranteBasicoModel> listar();
	
	
//	@ApiIgnore
	@Operation(summary = "Lista os restaurantes", hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNome();
	
	
	
	@Operation(summary = "Busca um restaurante por ID")
	public RestauranteModel buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
	@Operation(summary = "Exclui um restaurante por ID")
	public void delete(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId);
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Ativa um restaurante por ID")
	public ResponseEntity<Void> ativar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restaurnateId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
//	})
	@Operation(summary = "Ativa restaurante por seus IDs")
	public void ativarRestaurantes(@Parameter(description = "IDs dos restaurantes", example = "1", required = true) List<Long> restauranteIds);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
//	})
	@Operation(summary = "Inativa restaurante por seus IDs")
	public void inativarRestaurantes(@Parameter(description = "IDs dos restaurantes", example = "1", required = true) List<Long> restauranteIds);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Inativa um restaurante por ID")
	public ResponseEntity<Void> inativar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Abre um restaurante por ID")
	public ResponseEntity<Void> abrir(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	@Operation(summary = "Fecha um restaurante por ID")
	public ResponseEntity<Void> fechar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);

}