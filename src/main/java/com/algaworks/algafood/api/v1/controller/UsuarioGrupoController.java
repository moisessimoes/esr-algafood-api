package com.algaworks.algafood.api.v1.controller;

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

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.UsuariosGrupoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuariosGrupoControllerOpenApi {
	
	@Autowired
	private AlgaLinks algaLinks;    
	
	@Autowired
	private AlgaSecurity algaSecurity; 
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private GrupoModelAssembler grupoModelAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos()).removeLinks();
        
        if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
        	
        	gruposModel.add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));
        	
        	gruposModel.getContent().forEach(grupoModel -> grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(usuarioId, grupoModel.getId(), "desassociar")));
        }
        
        return gruposModel;
    }
    
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	usuarioService.desassociarDoGrupo(usuarioId, grupoId);
    	return ResponseEntity.noContent().build();
    }

	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	usuarioService.associarAoGrupo(usuarioId, grupoId);
    	return ResponseEntity.noContent().build();
    }        
}