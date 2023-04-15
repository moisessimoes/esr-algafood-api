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

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.UsuariosGrupoControllerOpenApi;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuariosGrupoControllerOpenApi {
	
	@Autowired
	private AlgaLinks algaLinks;    
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private GrupoModelAssembler grupoModelAssembler;
	
	@GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
        															 .removeLinks()
        															 .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));
        
        gruposModel.getContent().forEach(grupoModel -> grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(usuarioId, grupoModel.getId(), "desassociar")));
        
        return gruposModel;
    }
    
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	usuarioService.desassociarDoGrupo(usuarioId, grupoId);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	usuarioService.associarAoGrupo(usuarioId, grupoId);
    	return ResponseEntity.noContent().build();
    }        
}