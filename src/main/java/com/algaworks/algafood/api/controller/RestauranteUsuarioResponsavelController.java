package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
	
	@GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        
        CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
        																   .removeLinks()
        																   .add(algaLinks.linkToResponsaveisRestaurante(restauranteId))
        																   .add(algaLinks.linkToResponsaveisRestauranteAssociacao(restauranteId, "associar"));
        
        usuariosModel.getContent().forEach(userModel -> userModel.add(algaLinks.linkToResponsaveisRestauranteDesassociacao(restauranteId, userModel.getId(), "desassociar")));
        
        return usuariosModel;
    }
    
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
    	restauranteService.desassociarResponsavel(restauranteId, usuarioId);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
    	restauranteService.associarResponsavel(restauranteId, usuarioId);
    	return ResponseEntity.noContent().build();
    }
}