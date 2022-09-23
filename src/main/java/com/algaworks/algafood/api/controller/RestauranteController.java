package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.algaworks.algafood.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@PostMapping
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		
		try {
			return restauranteService.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable("restauranteId") Long restauranteId, @RequestBody Restaurante restaurante) {
		
		Restaurante restauranteAtual = restauranteService.buscarPorId(restauranteId);
		
		String[] camposIgnorados = {"id", "formasPagamento", "endereco", "dataCadastro"};
		
		//BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
		BeanUtils.copyProperties(restaurante, restauranteAtual, camposIgnorados);
		
		try {
			return restauranteService.salvar(restauranteAtual);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	//===================================================================================================================================
	
	//ATUALIZACAO PARCIAL DE RECURSOS COM O PATCH
	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable("restauranteId") Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest req) {
		
		Restaurante restauranteAtual = restauranteService.buscarPorId(restauranteId);
		
		merge(campos, restauranteAtual, req);
		
		return restauranteService.salvar(restauranteAtual);
	}

	
	//Atualização parcial com a API de Reflections do Spring
	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest req) {
		
		ServletServerHttpRequest server = new ServletServerHttpRequest(req);
		
		try {
			//Convertendo  os campos origem em um objeto Restaurante
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurante restauranteOrigem = mapper.convertValue(camposOrigem, Restaurante.class);
			
			camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				
				System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
				
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
			
		} catch (IllegalArgumentException e) {
			
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, server);
		}
	}
	
	//===================================================================================================================================
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	
	//Esse metodo lista os dados no formato XML
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhaXmlWrapper listarXml() {
//		return new CozinhaXmlWrapper(cozinhaRepository.findAll());
//	}
	
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable("restauranteId") Long restauranteId) {
		return restauranteService.buscarPorId(restauranteId);
		//return restauranteRepository.findById(restauranteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.excluir(restauranteId);
	}
}