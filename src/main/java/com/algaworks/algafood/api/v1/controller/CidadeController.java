package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.algaworks.algafood.api.utils.ResourceUriHelper;
import com.algaworks.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.repositories.CidadeRepository;


//20.10. Preparando o projeto para versionamento da API por Media Type
//A ideia é substituir o MediaType fixo (MediaType.APPLICATION_JSON_VALUE) por um customizado
/*No cabeçalho da requisicao (Headers) a propriedade Accept ao invés de ser
 * application/json, agora vai ser application/vnd.algafood.v1+json, pois assim, é possivel diferenciar uma versão da outra
 * 
 * OBS: Já foi feito o versionamento por MediaType, agora vamos fazer o versionamento por URI
 * 
 * AlgaMediaType.V1_APPLICATION_JSON_VALUE
 * 
 * */

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping //(produces = AlgaMediaType.V1_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModel> listar() {
		
		List<Cidade> cidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(cidades);
		
		//19.10. Adicionando hypermedia na representação de recursos de coleção
		
		//CollectionModel<CidadeModel> cidadesCollectionModel = CollectionModel.of(cidadesModel);
		
		//cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());
	}
	
	
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping(path = "/{cidadeId}") //, produces = AlgaMediaType.V1_APPLICATION_JSON_VALUE)
	public CidadeModel buscar(@PathVariable("cidadeId") Long cidadeId) {
		
		Cidade cidade = cidadeService.buscarPorId(cidadeId);
		
		 CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
		 
		 //19.7. Adicionando hypermedia na representação de recurso único com HAL
		 //cidadeModel.add(Link.of("http://localhost:9090/cidades/1"));
		 
		 
		 //19.8. Construindo links dinâmicos com WebMvcLinkBuilder
		 //cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeModel.getId()).withSelfRel());
		 
		 //19.9. Construindo links que apontam para métodos
		 //cidadeModel.add(linkTo(methodOn(CidadeController.class).buscar(cidadeModel.getId())).withSelfRel());
		 
		 //cidadeModel.add(linkTo(CidadeController.class).withRel("cidades"));
		 //cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
		 
		 //cidadeModel.getEstado().add(linkTo(EstadoController.class).slash(cidadeModel.getEstado().getId()).withSelfRel());
		 //cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId())).withSelfRel());
		 
		 return cidadeModel;
		//return cidadeRepository.findById(cidadeId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@CheckSecurity.Cidades.PodeEditar
	@PostMapping//(produces = AlgaMediaType.V1_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidade) {
		
		try {
			
			Cidade city = cidadeInputDisassembler.toDomainObject(cidade);
			city = cidadeService.salvar(city);
			
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(city);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
			
			return cidadeModel;
			
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@CheckSecurity.Cidades.PodeEditar
	@PutMapping(path = "/{cidadeId}") //, produces = AlgaMediaType.V1_APPLICATION_JSON_VALUE)
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
	
	
	@CheckSecurity.Cidades.PodeEditar
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("cidadeId") Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
}