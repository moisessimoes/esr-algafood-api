package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private ModelMapper modelMapper;
	
    public PermissaoModel toModel(Permissao permissao) {
    	PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
        return permissaoModel;
    }
    
    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
    	return RepresentationModelAssembler.super.toCollectionModel(entities).add(algaLinks.linkToPermissoes());
    }
    
//    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
//        return permissoes.stream().map(permissao -> toModel(permissao)).collect(Collectors.toList());
//    }
}