package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.algaworks.algafood.repositories.ProdutoRepository;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler ProdutoInputDisassembler;
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long restauranteId) {
		
		Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
		
		Produto produto = ProdutoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = produtoService.salvar(produto);
		return produtoModelAssembler.toModel(produto);
	}
	
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long restauranteId, @PathVariable Long produtoId) {
		
		//Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
		Produto produto = produtoService.buscarPorId(restauranteId, produtoId);
		
		ProdutoInputDisassembler.copyToDomainObject(produtoInput, produto);
		produto = produtoService.salvar(produto);
		return produtoModelAssembler.toModel(produto);
	}
	
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId, @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
																 
		Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
		
		List<Produto> produtos = null;
		
		if (incluirInativos) {
			produtos = produtoRepository.findTodosByRestaurante(restaurante);
			
		} else {
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		
		return produtoModelAssembler.toCollectionModel(produtos).add(algaLinks.linkToProdutos(restauranteId));
	}
	
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscarPorId(restauranteId, produtoId);
		return produtoModelAssembler.toModel(produto);
	}
}