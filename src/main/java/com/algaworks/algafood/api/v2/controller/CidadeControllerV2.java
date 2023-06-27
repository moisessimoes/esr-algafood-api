package com.algaworks.algafood.api.v2.controller;

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
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.repositories.CidadeRepository;

import io.swagger.annotations.Api;


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

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 { //20.11. Implementando o versionamento da API por Media Type
	
	//implements CidadeControllerOpenApi
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssemblerV2;
	
	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassemblerV2;
	
	@GetMapping//(produces = AlgaMediaType.V2_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModelV2> listar() {
		
		List<Cidade> cidades = cidadeRepository.findAll();
		
		return cidadeModelAssemblerV2.toCollectionModel(cidades);
		
		//19.10. Adicionando hypermedia na representação de recursos de coleção
		
		//CollectionModel<CidadeModel> cidadesCollectionModel = CollectionModel.of(cidadesModel);
		
		//cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());
	}
	
	
	@GetMapping(path = "/{cidadeId}") //, produces = AlgaMediaType.V2_APPLICATION_JSON_VALUE)
	public CidadeModelV2 buscar(@PathVariable("cidadeId") Long cidadeId) {
		
		 Cidade cidade = cidadeService.buscarPorId(cidadeId);
		
		 CidadeModelV2 cidadeModel = cidadeModelAssemblerV2.toModel(cidade);
		 
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
	
	
	@PostMapping //(produces = AlgaMediaType.V2_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidade) {
		
		try {
			
			Cidade city = cidadeInputDisassemblerV2.toDomainObject(cidade);
			city = cidadeService.salvar(city);
			
			CidadeModelV2 cidadeModel = cidadeModelAssemblerV2.toModel(city);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
			
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@PutMapping(path = "/{cidadeId}") //, produces = AlgaMediaType.V2_APPLICATION_JSON_VALUE)
	public Cidade atualizar(@PathVariable("cidadeId") Long cidadeId, @RequestBody @Valid CidadeInputV2 cidade) {
		
		Cidade city = cidadeService.buscarPorId(cidadeId);
		//BeanUtils.copyProperties(cidade, city, "id");
		
		cidadeInputDisassemblerV2.copyToDomainObject(cidade, city);
		
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