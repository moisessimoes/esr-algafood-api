package com.algaworks.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.AlgaLinksV2;
import com.algaworks.algafood.api.v2.controller.CidadeControllerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {
	
	@Autowired
	private AlgaLinksV2 algaLinks;

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModelAssemblerV2() { //19.11. Montando modelo de representação com RepresentationModelAssembler
		super(CidadeControllerV2.class, CidadeModelV2.class);
	}
	
	@Override
	public CidadeModelV2 toModel(Cidade cidade) {
		
		CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(algaLinks.linkToCidades("cidades"));
		
		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToCidades());
	}
	
//	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
//		return cidades.stream().map(city -> toModel(city)).collect(Collectors.toList());
//	}
}