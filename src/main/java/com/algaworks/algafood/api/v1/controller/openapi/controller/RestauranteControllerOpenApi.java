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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

//@Api(tags = "Restaurantes")
@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerOpenApi {
	
//	@ApiOperation("Cadastra um novo restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 201, message = "Restaurante cadastrado"),
//	})
	public RestauranteModel adicionar(RestauranteInput restauranteInput);//@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true) RestauranteInput restaurante);
	
	
	
//	@ApiOperation("Atualiza um restaurante")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Restaurante atualizado"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId, 
			//@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true) RestauranteInput restaurante);
	
	
	
	//@ApiOperation("Atualiza um restaurante parcilamente")
	public Restaurante atualizarParcial(Long restaurnateId,//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId, 
			Map<String, Object> campos, HttpServletRequest req);
	
//	@ApiOperation(value = "Lista restaurantes", response = RestaurantesModelOpenApi.class)
//	@ApiImplicitParams({ //18.28. Descrevendo parâmetros de projeções em endpoints de consultas
//    	@ApiImplicitParam(name = "projecao", value = "Nome da projeção de pedidos.", paramType = "query", type = "string", allowableValues = "apenas-nome")
//    })
	//@JsonView(RestauranteView.Resumo.class)
	public CollectionModel<RestauranteBasicoModel> listar();
	
	
//	@ApiIgnore
//	@ApiOperation(value = "Lista restaurantes", hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNome();
	
	
	
	//@ApiOperation("Busca o restaurante pelo ID")
	public RestauranteModel buscar(Long restauranteId);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	
	
	//@ApiOperation("Exclui o restaurante pelo ID")
	public void delete(Long restaurnateId);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Ativa o restaurante pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> ativar(Long restaurnateId);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Ativa os restaurantes pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
//	})
	public void ativarRestaurantes(List<Long> restauranteIds);//@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);
	
	
	
//	@ApiOperation("Inativa os restaurantes pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
//	})
	public void inativarRestaurantes(List<Long> restauranteIds);//@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);
	
	
	
//	@ApiOperation("Inativa o restaurante pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> inativar(Long restauranteId);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Abri o restaurante pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> abrir(Long restauranteId);//@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	
	
//	@ApiOperation("Fecha o restaurante pelo ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
//		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
//	})
	public ResponseEntity<Void> fechar(Long restauranteId);

}