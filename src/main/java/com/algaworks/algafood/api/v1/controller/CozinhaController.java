package com.algaworks.algafood.api.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.algaworks.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.repositories.CozinhaRepository;

@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {
	
	private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler; //19.15. Adicionando hypermedia em recursos com paginação
	
	
	//@PreAuthorize("hasAuthority('EDITAR_COZINHAS')") //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
	@CheckSecurity.Cozinhas.PodeEditar //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid CozinhaInput cozinha) {
		Cozinha kitchen = cozinhaInputDisassembler.toDomainObject(cozinha);
		return cozinhaService.salvar(kitchen);
	}
	
	
	//@PreAuthorize("hasAuthority('EDITAR_COZINHAS')") //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
	@CheckSecurity.Cozinhas.PodeEditar //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody @Valid CozinhaInput cozinha) {
		
		Cozinha cozinhaAtual = cozinhaService.buscarPorId(cozinhaId);
			
		cozinhaInputDisassembler.copyToDomainObject(cozinha, cozinhaAtual);
		//BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return cozinhaService.salvar(cozinhaAtual);
	}
	
//	@GetMapping
//	public List<CozinhaModel> listar() {
//		return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
//	}
	
	//@PreAuthorize("isAuthenticated()") //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
	@CheckSecurity.Cozinhas.PodeConsultar //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	@GetMapping
	public PagedModel<CozinhaModel> listarComPaginacao(@PageableDefault(size = 10) Pageable pageable) {
		
		//@PageableDefault(size = 10) - Para definir uma qtd fixa de elementos
		
		//21.1. Introdução ao Logback e SLF4J
		logger.info("Consultando cozinhas com páginas de {} registros", pageable.getPageSize());
		
//		if (true) {
//			//21.2. Desafio: registrando logs de exceptions não tratadas
//			throw new RuntimeException("Teste de Exception");
//		}
		
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
//		List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());
//		Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalElements());
		
		//19.15. Adicionando hypermedia em recursos com paginação
		
		PagedModel<CozinhaModel> pagedCozinhasModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return pagedCozinhasModel;
	}
	
	//Esse metodo lista os dados no formato XML
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhaXmlWrapper listarXml() {
//		return new CozinhaXmlWrapper(cozinhaRepository.findAll());
//	}
	
	
	//@PreAuthorize("isAuthenticated()") //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
	@CheckSecurity.Cozinhas.PodeConsultar //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable("cozinhaId") Long id, HttpServletRequest req) {
		
		ServletServerHttpRequest server = new ServletServerHttpRequest(req);
		
		try {
			return cozinhaModelAssembler.toModel(cozinhaService.buscarPorId(id));
			//return cozinhaRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		} catch (MethodArgumentTypeMismatchException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, server);
		}
	}
	
	
	//@PreAuthorize("hasAuthority('EDITAR_COZINHAS')") //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
	@CheckSecurity.Cozinhas.PodeEditar //23.23. Simplificando o controle de acesso em métodos com meta-anotações
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("cozinhaId") Long cozinhaId) {
		cozinhaService.excluir(cozinhaId);
	}
}