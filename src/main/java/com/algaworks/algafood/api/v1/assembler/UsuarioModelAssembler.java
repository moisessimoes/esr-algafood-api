package com.algaworks.algafood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private AlgaSecurity algaSecurity; 
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}
    
	@Override
    public UsuarioModel toModel(Usuario usuario) {
        
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
		
		usuarioModel.add(algaLinks.linkToUsuario(usuarioModel.getId()));
		
		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			
			usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));
			usuarioModel.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		}
		
		return usuarioModel;
    }
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
	}
    
//    public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios) {
//        return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
//    }    
}