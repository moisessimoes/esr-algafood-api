package com.algaworks.algafood.api.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.repositories.CidadeRepository;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable("cidadeId") Long cidadeId) {
		return cidadeModelAssembler.toModel(cidadeService.buscarPorId(cidadeId));
		//return cidadeRepository.findById(cidadeId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidade) {
		
		try {
			
			Cidade city = cidadeInputDisassembler.toDomainObject(cidade);
			city = cidadeService.salvar(city);
			
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(city);
			
			//19.2. Adicionando a URI do recurso criado no header da resposta (HATEOAS)
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cidadeModel.getId()).toUri();
			
			//Capturando o response da requisição e adicionando o URI completa no cabeçalho
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.setHeader(HttpHeaders.LOCATION, uri.toString());
			
			return cidadeModel;
			
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable("cidadeId") Long cidadeId, @RequestBody @Valid CidadeInput cidade) {
		
		Cidade city = cidadeService.buscarPorId(cidadeId);
		//BeanUtils.copyProperties(cidade, city, "id");
		
		cidadeInputDisassembler.copyToDomainObject(cidade, city);
		
		try {
			return cidadeService.salvar(city);
			
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("cidadeId") Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
}