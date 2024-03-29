package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CidadeController;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
	
	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaSecurity algaSecurity; 
	
	public CidadeModelAssembler() { //19.11. Montando modelo de representação com RepresentationModelAssembler
		super(CidadeController.class, CidadeModel.class);
	}
	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		
		//CidadeModel cidadeModel = modelMapper.map(cidade, CidadeModel.class);
		
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		if (algaSecurity.podeConsultar()) {
			
			cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
			cidadeModel.add(algaLinks.linkToCidades("cidades"));
		}
		
		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		
		CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultar()) {
			collectionModel.add(algaLinks.linkToCidades());
		}
		
		return collectionModel;
	}
	
//	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
//		return cidades.stream().map(city -> toModel(city)).collect(Collectors.toList());
//	}
}