package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
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
	private SmartValidator validator;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	@PostMapping
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restaurante) {
		
		try {
			Restaurante rest = restauranteInputDisassembler.toDomainObject(restaurante);
			return restauranteModelAssembler.toModel(restauranteService.salvar(rest));
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable("restauranteId") Long restauranteId, @RequestBody @Valid RestauranteInput restaurante) {
		
		Restaurante restauranteAtual = restauranteService.buscarPorId(restauranteId);
		
		restauranteInputDisassembler.copyToDomainObject(restaurante, restauranteAtual);
		
		//String[] camposIgnorados = {"id", "formasPagamento", "endereco", "dataCadastro"};
		
		//BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
		//BeanUtils.copyProperties(restaurante, restauranteAtual, camposIgnorados);
		
		try {
			return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	//===================================================================================================================================
	
	//ATUALIZACAO PARCIAL DE RECURSOS COM O PATCH
	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable("restauranteId") Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest req) {
		
		Restaurante restauranteAtual = restauranteService.buscarPorId(restauranteId);
		
		merge(campos, restauranteAtual, req);
		
		validate(restauranteAtual, "restaurante");
		
		return restauranteService.salvar(restauranteAtual);
	}

	
	private void validate(Restaurante restaurante, String objectName) {
		
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
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
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	
	//Esse metodo lista os dados no formato XML
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhaXmlWrapper listarXml() {
//		return new CozinhaXmlWrapper(cozinhaRepository.findAll());
//	}
	
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable("restauranteId") Long restauranteId) {
		return restauranteModelAssembler.toModel(restauranteService.buscarPorId(restauranteId));
		//return restauranteRepository.findById(restauranteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.excluir(restauranteId);
	}
	
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}
	
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurantes(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarRestaurantes(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}
	
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.abrir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable("restauranteId") Long restauranteId) {
		restauranteService.fechar(restauranteId);
	}
}