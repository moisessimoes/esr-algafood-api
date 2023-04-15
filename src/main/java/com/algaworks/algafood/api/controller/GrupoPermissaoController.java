package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		
		Grupo grupo = grupoService.buscarPorId(grupoId);
		
		CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
																				 .removeLinks()
																				 .add(algaLinks.linkToGrupoPermissoes(grupoId))
																				 .add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
		
		permissoesModel.getContent().forEach(permissaoModel -> permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(grupoId, permissaoModel.getId(), "dessasociar")));
		
		return permissoesModel;
	}
	
//	@GetMapping("/{permissaoId}")
//	public PermissaoModel buscar(@PathVariable Long permissaoId) {
//		Permissao permissao = permissaoService.buscarPorId(permissaoId);
//		return permissaoModelAssembler.toModel(permissao);
//	}
}