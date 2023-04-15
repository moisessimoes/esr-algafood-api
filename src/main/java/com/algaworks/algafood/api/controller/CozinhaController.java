package com.algaworks.algafood.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
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

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.repositories.CozinhaRepository;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {
	
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid CozinhaInput cozinha) {
		Cozinha kitchen = cozinhaInputDisassembler.toDomainObject(cozinha);
		return cozinhaService.salvar(kitchen);
	}
	
	
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
	
	@GetMapping
	public PagedModel<CozinhaModel> listarComPaginacao(@PageableDefault(size = 10) Pageable pageable) {
		
		//@PageableDefault(size = 10) - Para definir uma qtd fixa de elementos
		
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
	
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("cozinhaId") Long cozinhaId) {
		cozinhaService.excluir(cozinhaId);
	}
}