package com.algaworks.algafood.api.v1.controller;

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

import com.algaworks.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.GruposControllerOpenApi;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.repositories.GrupoRepository;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GruposControllerOpenApi {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<GrupoModel> listar() {
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable("grupoId") Long grupoId) {
		return grupoModelAssembler.toModel(grupoService.buscarPorId(grupoId));
		//return estadoRepository.findById(estadoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo adicionar(@RequestBody @Valid GrupoInput grupo) {
		Grupo group = grupoInputDisassembler.toDomainObject(grupo);
		return grupoService.salvar(group);
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{grupoId}")
	public Grupo atualizar(@PathVariable("grupoId") Long grupoId, @RequestBody @Valid GrupoInput grupo) {
		
		Grupo group = grupoService.buscarPorId(grupoId);
		
		grupoInputDisassembler.copyToDomainObject(grupo, group);
		
		//BeanUtils.copyProperties(estado, state, "id");
		return grupoService.salvar(group);
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("grupoId") Long grupoId) {
		grupoService.excluir(grupoId);
	}
}