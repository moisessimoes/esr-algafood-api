package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		
		FotoProdutoModel fotoProdutoModel = createModelWithId(fotoProduto.getId(), fotoProduto, fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId());
		
		modelMapper.map(fotoProduto, fotoProdutoModel);
		
		fotoProdutoModel.add(algaLinks.linkToProdutos(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId(), "produto"));
		
		return fotoProdutoModel;
	}
}